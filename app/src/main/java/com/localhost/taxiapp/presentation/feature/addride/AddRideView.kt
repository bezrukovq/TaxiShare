package com.localhost.taxiapp.presentation.feature.addride

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.localhost.taxiapp.data.ride.PostRideResponse

@StateStrategyType(AddToEndSingleStrategy::class)
interface AddRideView : MvpView {
    fun setUpSpinners(stopsList: Array<String>, placesList: Array<Int>)
    fun postResult(response: PostRideResponse)
}
