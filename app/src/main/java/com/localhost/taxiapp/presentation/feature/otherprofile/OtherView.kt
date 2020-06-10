package com.localhost.taxiapp.presentation.feature.otherprofile

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.md.nails.presentation.basemvp.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface OtherView: BaseView {
    fun added(response :PostRideResponse)
}
