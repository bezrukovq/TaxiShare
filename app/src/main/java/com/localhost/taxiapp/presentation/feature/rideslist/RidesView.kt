package com.localhost.taxiapp.presentation.feature.rideslist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.ride.Ride
import com.md.nails.presentation.basemvp.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface RidesView : BaseView {
    fun setList(list: List<Ride>)
    @StateStrategyType(SkipStrategy::class)
    fun joinResult(response: PostRideResponse)
    @StateStrategyType(SkipStrategy::class)
    fun showError(message: String)
}
