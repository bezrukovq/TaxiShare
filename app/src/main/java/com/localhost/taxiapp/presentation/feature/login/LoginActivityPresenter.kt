package com.localhost.taxiapp.presentation.feature.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.localhost.taxiapp.domain.user.UserModel
import com.md.nails.presentation.basemvp.BasePresenter
import javax.inject.Inject

@InjectViewState
class LoginActivityPresenter
    @Inject constructor(val model: UserModel) : BasePresenter<LoginView>() {

    fun login() = viewState.loginVK()

    fun setUser() = model.setUser(this)
}
