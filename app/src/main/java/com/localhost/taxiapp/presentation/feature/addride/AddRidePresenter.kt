package com.localhost.taxiapp.presentation.feature.addride

import com.arellomobile.mvp.InjectViewState
import com.localhost.taxiapp.data.ride.NewRidePost
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
import com.localhost.taxiapp.presentation.base.launchCatching
import com.md.nails.presentation.basemvp.BasePresenter
import javax.inject.Inject

@InjectViewState
class AddRidePresenter
@Inject constructor(val model: RideModel, val userModel: UserModel) : BasePresenter<AddRideView>() {

    override fun onFirstViewAttach() {
        viewState.setUpSpinners(model.stopsList, model.placesList)
    }

    fun postRide(startPoint: String, destPoint: String, places: Int, hours: Int, minutes: Int) {
        launchCatching(
            func = {
                model.postRide(
                    NewRidePost(
                        userModel.curUser?.screen_name.toString(),
                        startPoint,
                        destPoint,
                        places,
                        hours,
                        minutes
                    )
                )
            },
            onSuccess = {
                    viewState.postResult(it)
            },
            onError = {
                    viewState.postResult(PostRideResponse(false, it.message.toString()))
            })
    }

}
