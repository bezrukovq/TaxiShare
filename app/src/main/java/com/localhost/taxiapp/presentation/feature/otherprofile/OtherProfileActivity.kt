package com.localhost.taxiapp.presentation.feature.otherprofile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.gson.Gson
import com.localhost.taxiapp.R
import com.localhost.taxiapp.TaxiApplication
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.user.UserForListWithPic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_other_profile.*
import javax.inject.Inject

class OtherProfileActivity : MvpAppCompatActivity(), OtherView {

    lateinit var user: UserForListWithPic

    @Inject
    @InjectPresenter
    lateinit var presenter: OtherPresenter

    @ProvidePresenter
    fun initPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        TaxiApplication.appComponent.friendsComponent().build().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_profile)
        user = Gson().fromJson(intent.getStringExtra("user"), UserForListWithPic::class.java)
        setUpProfile()
    }

    override fun onResume() {
        super.onResume()
        title = getString(R.string.profile)
    }

    fun setUpProfile() {
        Picasso.get().load(user.picURL).into(profile_image)
        tv_name.text = user.name
        if (user.friend)
            btn_add.visibility = View.GONE
        btn_vk.setOnClickListener { x -> openVK() }
        btn_add.setOnClickListener { x -> add()}
    }

    fun openVK() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://vk.com/" + user.screen_name))
        startActivity(browserIntent)
    }

    private fun add(){
        btn_add.isClickable = false
        tv_adding.visibility = View.VISIBLE
        pb_adding.visibility = View.VISIBLE
        presenter.add(user)
    }

    override fun added(response: PostRideResponse) {
        tv_adding.visibility = View.GONE
        pb_adding.visibility = View.GONE
        if (response.successful) {
            btn_add.visibility = View.GONE
            Toast.makeText(this,response.message,Toast.LENGTH_LONG).show()
        }else{
            btn_add.isClickable = true
            Toast.makeText(this,response.message,Toast.LENGTH_LONG).show()
        }
    }
}
