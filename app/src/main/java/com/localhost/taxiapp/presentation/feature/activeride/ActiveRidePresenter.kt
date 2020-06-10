package com.localhost.taxiapp.presentation.feature.activeride

import com.arellomobile.mvp.InjectViewState
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
import com.localhost.taxiapp.presentation.base.launchCatching
import com.md.nails.presentation.basemvp.BasePresenter
import javax.inject.Inject

@InjectViewState
class ActiveRidePresenter
@Inject constructor(val rideModel: RideModel, val userModel: UserModel) :
    BasePresenter<ActiveRideView>() {

    override fun onFirstViewAttach() {
        refresh()
    }

    fun refresh() {
        launchCatching(
            func = {
                rideModel.getCurrRide(userModel.curUser?.screen_name)
            },
            onSuccess = {
                if (it.id != -1) {
                    viewState.setRide(it)
                    launchCatching(
                        func = {
                            rideModel.getPassengers(
                                it.id,
                                userModel.curUser?.screen_name.toString()
                            )
                        },
                        onSuccess = {
                            viewState.setList(userModel.convertForList(it))
                        },
                        onError = { viewState.showError(it.message) }
                    )
                } else {
                    viewState.setRide(it)
                    viewState.setList(arrayListOf())
                    viewState.showNoRide()
                }
            },
            onError = {
                viewState.showError(it.message)
            }
        )
    }

    fun rideAction(rideId: Int, action: String) {
        launchCatching(
            func = {
                rideModel.rideAction(rideId, userModel.curUser?.screen_name, action)
            },
            onSuccess = {
                viewState.finish(it)
            },
            onError = {
                viewState.showError(it.message)
            })
    }

}
