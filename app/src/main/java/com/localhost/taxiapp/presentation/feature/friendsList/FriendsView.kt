package com.localhost.taxiapp.presentation.feature.friendsList

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.localhost.taxiapp.data.user.UserForListWithPic

@StateStrategyType(AddToEndSingleStrategy::class)
interface FriendsView: MvpView {
    fun setList(list: List<UserForListWithPic>)
}
