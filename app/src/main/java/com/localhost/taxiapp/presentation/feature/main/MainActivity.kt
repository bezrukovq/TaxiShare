package com.localhost.taxiapp.presentation.feature.main

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.localhost.taxiapp.R
import com.localhost.taxiapp.TaxiApplication
import com.localhost.taxiapp.domain.helpers.NavigationHistory
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    @ProvidePresenter
    fun initPresenter() = presenter

    val navigationHistory = NavigationHistory()

    private var navigator: Navigator = SupportAppNavigator(this, R.id.container)

    @Inject
    lateinit var navigatorHolder : NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        TaxiApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationHistory.pushItem(R.id.nav_rides)
        navigation.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                if (navigation.selectedItemId != item.itemId) {
                    navigationHistory.pushItem(item.itemId)
                    when (item.itemId) {
                        R.id.nav_rides ->
                            presenter.goToRidesList()
                        R.id.nav_active ->
                            presenter.goToCurrentRide()
                        R.id.nav_user ->
                            presenter.goToProfile()
                        R.id.nav_history ->
                            presenter.goToHistory()
                        R.id.nav_stops ->
                            presenter.goToMap()
                    }
                }
                return true
            }
        })
    }

    override fun onBackPressed() {
        if (navigationHistory.size()!=1)
            navigation.menu.findItem(navigationHistory.onBackPressed()).setChecked(true)
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun init() {
        navigation.menu.findItem(R.id.nav_rides).setChecked(true)
    }
}
