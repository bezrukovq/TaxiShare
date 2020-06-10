package com.localhost.taxiapp.presentation.feature.rideslist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.localhost.taxiapp.R
import com.localhost.taxiapp.TaxiApplication
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.ride.Ride
import com.localhost.taxiapp.presentation.feature.addride.AddRideActivity
import com.md.nails.presentation.basemvp.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_rides_list.*
import kotlinx.android.synthetic.main.fragment_rides_list.swipe_refresh
import javax.inject.Inject

class RidesListFragment : BaseMvpFragment(),
    RidesView, SwipeRefreshLayout.OnRefreshListener, JoinRideCallback {

    private var connecting = false

    private var adapter: RidesAdapter =
        RidesAdapter(this)

    @Inject
    @InjectPresenter
    lateinit var presenter: RidesListPresenter

    @ProvidePresenter
    fun initPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        TaxiApplication.rideComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.rides_list)
    }

    override val layoutId: Int
        get() = R.layout.fragment_rides_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_rides_list.adapter = adapter
        swipe_refresh.setOnRefreshListener(this)
        fab.setOnClickListener { fabClick() }
    }

    private fun fabClick() {
        startActivity(Intent(context, AddRideActivity::class.java))
    }

    override fun onRefresh() {
        swipe_refresh.isRefreshing = true
        presenter.refresh()
    }

    override fun setList(list: List<Ride>) {
        swipe_refresh.isRefreshing = false
        adapter.rides = list
        adapter.notifyDataSetChanged()
    }

    override fun showError(message: String) {
        swipe_refresh.isRefreshing = false
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    override fun join(rideId: Int) {
        if (!connecting){
            connecting=true
            presenter.join(rideId)
        }
    }

    override fun joinResult(response: PostRideResponse) {
        if(response.successful)
            Toast.makeText(context,getString(R.string.success_join),Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,response.message,Toast.LENGTH_LONG).show()
        connecting=false
    }

}
