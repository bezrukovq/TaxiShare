package com.localhost.taxiapp.presentation.feature.activeride

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.ride.Ride
import com.localhost.taxiapp.data.user.UserForListWithPic

@StateStrategyType(AddToEndSingleStrategy::class)
interface ActiveRideView : MvpView {
    fun setList(list: List<UserForListWithPic>)
    @StateStrategyType(SingleStateStrategy::class)
    fun setRide(ride: Ride)
    @StateStrategyType(SingleStateStrategy::class)
    fun showNoRide()
    fun finish(response: PostRideResponse)
    fun showError(message: String?)
}
