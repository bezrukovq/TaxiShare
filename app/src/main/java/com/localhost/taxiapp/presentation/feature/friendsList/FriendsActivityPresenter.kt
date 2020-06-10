package com.localhost.taxiapp.presentation.feature.friendsList

import com.arellomobile.mvp.InjectViewState
import com.localhost.taxiapp.data.user.UserForListWithPic
import com.localhost.taxiapp.domain.user.UserModel
import com.localhost.taxiapp.presentation.base.launchCatching
import com.md.nails.presentation.basemvp.BasePresenter
import java.util.ArrayList
import javax.inject.Inject

@InjectViewState
class FriendsActivityPresenter
@Inject constructor(val model: UserModel) : BasePresenter<FriendsView>() {


    val list = ArrayList<UserForListWithPic>()

    fun setList(user: String) {
        launchCatching(
            func = {
                model.getFriends(user)
            },
            onSuccess = {
                viewState.setList(model.convertForList(it))
            },
            onError = {}
        )
    }
}
