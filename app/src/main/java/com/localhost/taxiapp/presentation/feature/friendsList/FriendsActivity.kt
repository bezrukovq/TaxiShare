package com.localhost.taxiapp.presentation.feature.friendsList

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair as UtilPair
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.gson.Gson
import com.localhost.taxiapp.R
import com.localhost.taxiapp.TaxiApplication
import com.localhost.taxiapp.data.user.UserForListWithPic
import com.localhost.taxiapp.presentation.feature.otherprofile.OtherProfileActivity
import kotlinx.android.synthetic.main.activity_friends.*
import javax.inject.Inject
import androidx.core.view.ViewCompat
import android.view.View
import com.localhost.taxiapp.presentation.feature.userlist.UsersAdapter
import com.localhost.taxiapp.presentation.feature.userlist.OpenProfileCallback

class FriendsActivity : MvpAppCompatActivity(), FriendsView, OpenProfileCallback {

    private var adapter: UsersAdapter =
        UsersAdapter(this)

    @Inject
    @InjectPresenter
    lateinit var presenter: FriendsActivityPresenter

    @ProvidePresenter
    fun initPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        TaxiApplication.appComponent.friendsComponent().build().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        rv_friends_list.adapter = adapter
        presenter.setList(intent.getStringExtra("screen_name"))
    }

    override fun onResume() {
        super.onResume()
        title = getString(R.string.friends)
    }

    override fun setList(list: List<UserForListWithPic>) {
        adapter.users = list
        adapter.notifyDataSetChanged()
    }

    override fun open(userForListWithPic: UserForListWithPic, textView: View, imageView: View) {
        val intent = Intent(this, OtherProfileActivity::class.java)
        intent.putExtra("user", Gson().toJson(userForListWithPic))
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            UtilPair.create(textView, ViewCompat.getTransitionName(textView).toString()),
            UtilPair.create(imageView, ViewCompat.getTransitionName(imageView).toString())
        )
        startActivity(intent, options.toBundle())
    }
}

