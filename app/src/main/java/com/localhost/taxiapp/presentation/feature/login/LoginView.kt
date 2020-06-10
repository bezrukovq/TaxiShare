package com.localhost.taxiapp.presentation.feature.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.md.nails.presentation.basemvp.BaseView

@StateStrategyType(OneExecutionStateStrategy::class)
interface LoginView : BaseView {
    fun loginVK()
}
