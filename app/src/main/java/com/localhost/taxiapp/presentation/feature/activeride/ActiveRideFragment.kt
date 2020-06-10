package com.localhost.taxiapp.presentation.feature.activeride

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.gson.Gson
import com.localhost.taxiapp.R
import com.localhost.taxiapp.TaxiApplication
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.ride.Ride
import com.localhost.taxiapp.data.user.UserForListWithPic
import com.localhost.taxiapp.presentation.feature.otherprofile.OtherProfileActivity
import com.localhost.taxiapp.presentation.feature.userlist.OpenProfileCallback
import com.localhost.taxiapp.presentation.feature.userlist.UsersAdapter
import com.md.nails.presentation.basemvp.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_active_ride.*
import kotlinx.android.synthetic.main.fragment_active_ride.swipe_refresh
import javax.inject.Inject

class ActiveRideFragment :
    BaseMvpFragment(), ActiveRideView, OpenProfileCallback, SwipeRefreshLayout.OnRefreshListener {

    private var adapter: UsersAdapter =
        UsersAdapter(this)

    @Inject
    @InjectPresenter
    lateinit var presenter: ActiveRidePresenter

    @ProvidePresenter
    fun initPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        TaxiApplication.rideComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override val layoutId: Int
        get() = R.layout.fragment_active_ride

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_users_list.adapter = adapter
        swipe_refresh.setOnRefreshListener(this)
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.active_ride)
    }

    override fun onRefresh() {
        swipe_refresh.isRefreshing = true
        presenter.refresh()
    }

    override fun setList(list: List<UserForListWithPic>) {
        swipe_refresh.isRefreshing = false
        adapter.users = list
        adapter.notifyDataSetChanged()
    }

    override fun setRide(ride: Ride) {
        tv_startpoint.text = ride.startPoint
        tv_destpoint.text = ride.destPoint
        tv_places_count.text = ride.places.toString()
        tv_time.text = ride.time
        tv_places_text.text = context?.resources?.getQuantityString(R.plurals.places, ride.places)
        setUpButtons(ride.id)
    }

    private fun setUpButtons(rideId: Int) {
        if (rideId < 0) {
            btn_finish.isClickable = false
            btn_decline.isClickable = false
        } else {
            btn_finish.setOnClickListener { x -> btnClick(rideId, "finish") }
            btn_decline.setOnClickListener { x -> btnClick(rideId, "decline") }
        }
    }

    private fun btnClick(rideId: Int, action: String) {
        btn_finish.isClickable = false
        btn_decline.isClickable = false
        presenter.rideAction(rideId, action)
    }

    override fun showNoRide() {
        swipe_refresh.isRefreshing = false
        Toast.makeText(context, getString(R.string.no_active_ride), Toast.LENGTH_LONG).show()
    }

    override fun finish(response: PostRideResponse) {
        if (response.successful) {
            presenter.refresh()
        } else
            Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
    }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        swipe_refresh.isRefreshing = false
        btn_finish.isClickable = true
        btn_decline.isClickable = true
    }

    override fun open(userForListWithPic: UserForListWithPic, textView: View, imageView: View) {
        val intent = Intent(activity, OtherProfileActivity::class.java)
        intent.putExtra("user", Gson().toJson(userForListWithPic))
        val options = ActivityOptions.makeSceneTransitionAnimation(
            activity,
            Pair.create(textView, ViewCompat.getTransitionName(textView).toString()),
            Pair.create(imageView, ViewCompat.getTransitionName(imageView).toString())
        )
        startActivity(intent, options.toBundle())
    }
}
