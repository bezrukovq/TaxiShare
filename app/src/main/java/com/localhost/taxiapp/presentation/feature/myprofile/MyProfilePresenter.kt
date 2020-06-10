package com.localhost.taxiapp.presentation.feature.myprofile

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.localhost.taxiapp.domain.user.UserModel
import javax.inject.Inject

@InjectViewState
class MyProfilePresenter
@Inject constructor(val model: UserModel) : MvpPresenter<MyProfileView>() {
    override fun onFirstViewAttach() {
        model.curUser?.let { viewState.setUserInfo(it) }
        model.curUserPic?.let { viewState.setPic(it) }
    }

    fun openVK() {
        model.curUser?.screen_name?.let { viewState.openVK(it) }
    }

    fun openFriends() {
        model.curUser?.screen_name?.let { viewState.openFriends(it) }
    }
}
