package com.localhost.taxiapp.presentation.feature.history

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@InjectViewState
class HistoryPresenter
@Inject constructor(val rideModel: RideModel, val userModel: UserModel) : MvpPresenter<HistoryView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() =
        refresh()

    fun refresh() {
        val disposable = rideModel.getHistory(userModel.curUser?.screen_name.toString()).subscribeBy(
            onSuccess = {
                rideModel.saveHistory(it)
                viewState.setList(it)
            },
            onError = {
                rideModel.getHistoryDB().subscribeBy(onSuccess = {
                    viewState.setList(it)
                },
                    onError = {})
            })
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: HistoryView?) {
        super.destroyView(view)
        compositeDisposable.clear()
    }
}
