package com.localhost.taxiapp.presentation.feature.history

import com.arellomobile.mvp.InjectViewState
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
import com.localhost.taxiapp.presentation.base.launchCatching
import com.localhost.taxiapp.presentation.base.runOnIO
import com.md.nails.presentation.basemvp.BasePresenter
import kotlinx.coroutines.async
import javax.inject.Inject

@InjectViewState
class HistoryPresenter
@Inject constructor(val rideModel: RideModel, val userModel: UserModel) :
    BasePresenter<HistoryView>() {

    override fun onFirstViewAttach() =
        refresh()

    fun refresh() {
        launchCatching(
            func = {
                rideModel.getHistory(userModel.curUser?.screen_name.toString())
            },
            onSuccess = {
                runOnIO { rideModel.saveHistory(it) }
                viewState.setList(it)
            },
            onError = {
                launchCatching(
                    func = {
                        async { rideModel.getHistoryDB() }.await()
                    },
                    onSuccess = {
                        viewState.setList(it)
                    }, onError = { })
            }
        )
    }
}

