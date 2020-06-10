package com.localhost.taxiapp.presentation.feature.history

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.localhost.taxiapp.data.ride.Ride
import com.md.nails.presentation.basemvp.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface HistoryView : BaseView {
    fun setList(list: List<Ride>)
}
