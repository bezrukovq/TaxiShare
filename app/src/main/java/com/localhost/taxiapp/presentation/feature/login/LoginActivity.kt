package com.localhost.taxiapp.presentation.feature.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.localhost.taxiapp.R
import com.localhost.taxiapp.TaxiApplication
import com.localhost.taxiapp.presentation.feature.main.MainActivity
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : MvpAppCompatActivity(), LoginView {

    @Inject
    @InjectPresenter
    lateinit var loginActivityPresenter: LoginActivityPresenter

    @ProvidePresenter
    fun initPresenter() = loginActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        TaxiApplication.appComponent.loginComponent().build().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        if (VKSdk.isLoggedIn())
            enterApp()
        btn_login.setOnClickListener { loginActivityPresenter.login() }
    }

    private fun enterApp() {
        loginActivityPresenter.setUser()
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }

    override fun loginVK() =
        VKSdk.login(this, VKScope.PHOTOS)

    override fun showMessage(text: String) {
        Toast.makeText(this,text,Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
                override fun onResult(res: VKAccessToken) {
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
                    enterApp()
                }

                override fun onError(error: VKError) {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                }
            })) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
