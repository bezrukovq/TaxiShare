package com.localhost.taxiapp.presentation.feature.stopslist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.localhost.taxiapp.domain.stop.StopModel
import javax.inject.Inject

@InjectViewState
class StopsListPresenter
@Inject constructor(val model: StopModel): MvpPresenter<StopsView>() {
    override fun onFirstViewAttach() {
        viewState.setList(model.getStops())
    }
}
