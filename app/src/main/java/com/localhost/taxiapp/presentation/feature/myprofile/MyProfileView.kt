package com.localhost.taxiapp.presentation.feature.myprofile

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.localhost.taxiapp.data.user.User

@StateStrategyType(AddToEndSingleStrategy::class)
interface MyProfileView: MvpView {
    fun setUserInfo(user: User)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openVK(userLink: String)
    fun setPic(url: String)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFriends(screen_name:String)
}
