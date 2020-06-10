package com.localhost.taxiapp.presentation.feature.friendsList

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.localhost.taxiapp.data.user.UserForListWithPic
import com.localhost.taxiapp.domain.user.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.ArrayList
import javax.inject.Inject

@InjectViewState
class FriendsActivityPresenter
@Inject constructor(val model: UserModel) : MvpPresenter<FriendsView>() {

    private val compositeDisposable = CompositeDisposable()

    val list = ArrayList<UserForListWithPic>()

    fun setList(user: String) {
        val disposable = model.getFriends(user).subscribeBy(
            onSuccess = {
                viewState.setList(model.convertForList(it))
            },
            onError = {}
        )
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: FriendsView?) {
        super.destroyView(view)
        compositeDisposable.clear()
    }
}
