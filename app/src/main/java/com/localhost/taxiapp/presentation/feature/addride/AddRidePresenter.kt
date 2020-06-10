package com.localhost.taxiapp.presentation.feature.addride

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.localhost.taxiapp.data.ride.NewRidePost
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@InjectViewState
class AddRidePresenter
@Inject constructor(val model: RideModel, val userModel: UserModel) : MvpPresenter<AddRideView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        viewState.setUpSpinners(model.stopsList, model.placesList)
    }

    fun postRide(startPoint: String, destPoint: String, places: Int, hours: Int, minutes: Int) {
        val disposable = model.postRide(
            NewRidePost(userModel.curUser?.screen_name.toString(), startPoint, destPoint, places, hours, minutes)
        )
            .subscribeBy(
                onSuccess = {
                    viewState.postResult(it)
                }
                , onError = {
                    viewState.postResult(PostRideResponse(false,it.message.toString()))
                })
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: AddRideView?) {
        super.destroyView(view)
        compositeDisposable.clear()
    }
}
