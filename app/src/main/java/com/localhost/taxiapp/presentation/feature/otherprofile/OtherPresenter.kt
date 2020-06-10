package com.localhost.taxiapp.presentation.feature.otherprofile

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.user.UserForListWithPic
import com.localhost.taxiapp.domain.user.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@InjectViewState
class OtherPresenter
@Inject constructor(val userModel: UserModel): MvpPresenter<OtherView>(){

    private val compositeDisposable = CompositeDisposable()

    fun add(user: UserForListWithPic){
       val disposable = userModel.addFriend(user).subscribeBy(
                onSuccess = {
                    viewState.added(it)
                },
                onError = {
                    viewState.added(PostRideResponse(false,it.message.toString()))
                }
        )
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: OtherView?) {
        super.destroyView(view)
        compositeDisposable.clear()
    }
}
