package com.localhost.taxiapp.presentation.feature

import androidx.fragment.app.Fragment
import com.localhost.taxiapp.presentation.feature.activeride.ActiveRideFragment
import com.localhost.taxiapp.presentation.feature.history.HistoryFragment
import com.localhost.taxiapp.presentation.feature.myprofile.MyProfileFragment
import com.localhost.taxiapp.presentation.feature.rideslist.RidesListFragment
import com.localhost.taxiapp.presentation.feature.stopslist.StopsListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    var ridesListFragment: RidesListFragment? = null
        get() {
            if (field == null)
                field = RidesListFragment()
            return field
        }

    class RidesListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? = ridesListFragment
    }

    var stopsListFragment: StopsListFragment? = null
        get() {
            if (field == null)
                field = StopsListFragment()
            return field
        }

    class StopsListScreen: SupportAppScreen(){
        override fun getFragment(): Fragment? = stopsListFragment
    }

    var historyFragment: HistoryFragment? = null
        get() {
            if (field == null)
                field = HistoryFragment()
            return field
        }

    class HistoryScreen: SupportAppScreen(){
        override fun getFragment(): Fragment? = historyFragment
    }

    var profileFragment: MyProfileFragment? = null
        get() {
            if (field == null)
                field = MyProfileFragment()
            return field
        }

    class ProfileScreen: SupportAppScreen(){
        override fun getFragment(): Fragment? = profileFragment
    }

    var activeRideFragment: ActiveRideFragment? = null
        get() {
            if (field == null)
                field = ActiveRideFragment()
            return field
        }

    class ActiveRideScreen: SupportAppScreen(){
        override fun getFragment(): Fragment? = activeRideFragment
    }
}
