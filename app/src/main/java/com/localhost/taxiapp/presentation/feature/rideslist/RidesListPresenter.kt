package com.localhost.taxiapp.presentation.feature.rideslist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@InjectViewState
class RidesListPresenter
@Inject constructor(val model: RideModel,val userModel: UserModel) : MvpPresenter<RidesView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() =
            refresh()

    fun refresh() {
        val disposable = model.getRidesList().subscribeBy(
                onSuccess = {
                    viewState.setList(it)
                },
                onError = {
                    viewState.showError(it.message.toString())
                })
        compositeDisposable.add(disposable)
    }

    fun join(rideId: Int) {
        val disposable = model.rideAction(rideId,userModel.curUser?.screen_name,"join").subscribeBy(
            onSuccess = {
                viewState.joinResult(it)
            },
            onError = {
                viewState.showError(it.message.toString())
            }
        )
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: RidesView?) {
        super.destroyView(view)
        compositeDisposable.clear()
    }
}
