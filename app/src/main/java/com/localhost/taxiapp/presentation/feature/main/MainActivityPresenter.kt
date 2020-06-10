package com.localhost.taxiapp.presentation.feature.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.localhost.taxiapp.domain.user.UserModel
import com.localhost.taxiapp.presentation.feature.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter
    @Inject constructor(val model: UserModel, var router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        viewState.init()
        router.newRootScreen(Screens.RidesListScreen())
    }

    fun goToRidesList() = router.navigateTo(Screens.RidesListScreen())

    fun goToProfile() = router.navigateTo(Screens.ProfileScreen())

    fun goToHistory() = router.navigateTo(Screens.HistoryScreen())

    fun goToCurrentRide() = router.navigateTo(Screens.ActiveRideScreen())

    fun goToMap() = router.navigateTo(Screens.StopsListScreen())
}
