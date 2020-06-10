package com.localhost.taxiapp.presentation.feature.rideslist

import com.arellomobile.mvp.InjectViewState
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
import com.localhost.taxiapp.presentation.base.launchCatching
import com.md.nails.presentation.basemvp.BasePresenter
import javax.inject.Inject

@InjectViewState
class RidesListPresenter
@Inject constructor(val model: RideModel, val userModel: UserModel) : BasePresenter<RidesView>() {

    override fun onFirstViewAttach() =
        refresh()

    fun refresh() {
        launchCatching(
            func = {
                model.getRidesList()
            },
            onSuccess = {
                viewState.setList(it)
            },
            onError = {
                viewState.showError(it.message.toString())
            })
    }

    fun join(rideId: Int) {
        launchCatching(
            func = {
                model.rideAction(rideId, userModel.curUser?.screen_name, "join")
            },
            onSuccess = {
                viewState.joinResult(it)
            },
            onError = {
                viewState.showError(it.message.toString())
            }
        )
    }
}
