package com.localhost.taxiapp.presentation.feature.history

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.localhost.taxiapp.data.ride.Ride

@StateStrategyType(AddToEndSingleStrategy::class)
interface HistoryView:MvpView {
    fun setList(list: List<Ride>)
}
