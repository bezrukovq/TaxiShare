package com.localhost.taxiapp.presentation.feature.activeride

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@InjectViewState
class ActiveRidePresenter
@Inject constructor(val rideModel: RideModel, val userModel: UserModel) : MvpPresenter<ActiveRideView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        refresh()
    }

    fun refresh() {
        val disposable = rideModel.getCurrRide(userModel.curUser?.screen_name).subscribeBy(
            onSuccess = {
                if (it.id != -1) {
                    viewState.setRide(it)
                    rideModel.getPassengers(it.id, userModel.curUser?.screen_name.toString()).subscribeBy(
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
        compositeDisposable.add(disposable)
    }

    fun rideAction(rideId: Int, action: String) {
        val disposable = rideModel.rideAction(rideId, userModel.curUser?.screen_name, action).subscribeBy(
            onSuccess = {
                viewState.finish(it)
            },
            onError = {
                viewState.showError(it.message)
            })
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: ActiveRideView?) {
        super.destroyView(view)
        compositeDisposable.clear()
    }
}
