package com.localhost.taxiapp.presentation.feature.stopslist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.localhost.taxiapp.data.stop.Stop

@StateStrategyType(AddToEndSingleStrategy::class)
interface StopsView: MvpView {
    fun setList(list: List<Stop>)
}
