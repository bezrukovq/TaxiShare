package com.localhost.taxiapp.presentation.feature.myprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.localhost.taxiapp.R
import com.localhost.taxiapp.TaxiApplication
import com.localhost.taxiapp.data.user.User
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import com.localhost.taxiapp.presentation.feature.friendsList.FriendsActivity
import com.squareup.picasso.Picasso

class MyProfileFragment : MvpAppCompatFragment(), MyProfileView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MyProfilePresenter

    @ProvidePresenter
    fun initPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        TaxiApplication.appComponent.userComponent().build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_vk.setOnClickListener { x -> presenter.openVK() }
        btn_friends.setOnClickListener { x-> presenter.openFriends() }
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.profile)
    }


    override fun openFriends(screen_name:String) {
        val intent = Intent(context, FriendsActivity::class.java)
        intent.putExtra("screen_name",screen_name)
        startActivity(intent)
    }

    override fun setUserInfo(user: User) {
        tv_name.text = NAME_FORMAT.format(user.first_name, user.last_name)
        tv_description.text = user.status
    }

    override fun openVK(userLink: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://vk.com/" + userLink))
        startActivity(browserIntent)
    }

    override fun setPic(url: String) {
        Picasso.get().load(url).into(profile_image);
    }

    companion object {
        private const val NAME_FORMAT = "%s %s"
    }
}
