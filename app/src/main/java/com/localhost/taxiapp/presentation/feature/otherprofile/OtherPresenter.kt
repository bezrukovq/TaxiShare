package com.localhost.taxiapp.presentation.feature.otherprofile

import com.arellomobile.mvp.InjectViewState
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.user.UserForListWithPic
import com.localhost.taxiapp.domain.user.UserModel
import com.localhost.taxiapp.presentation.base.launchCatching
import com.md.nails.presentation.basemvp.BasePresenter
import javax.inject.Inject

@InjectViewState
class OtherPresenter
@Inject constructor(val userModel: UserModel) : BasePresenter<OtherView>() {


    fun add(user: UserForListWithPic) {
        val request =
            launchCatching(
                func = {
                    userModel.addFriend(user)
                },
                onSuccess = {
                    viewState.added(it)
                },
                onError = {
                    viewState.added(PostRideResponse(false, it.message.toString()))
                }
            )
    }

}
