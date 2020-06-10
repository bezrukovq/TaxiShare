package com.localhost.taxiapp.presentation.di.component

import com.localhost.taxiapp.presentation.di.RideScope
import com.localhost.taxiapp.presentation.di.module.RideModule
import com.localhost.taxiapp.presentation.feature.activeride.ActiveRideFragment
import com.localhost.taxiapp.presentation.feature.addride.AddRideActivity
import com.localhost.taxiapp.presentation.feature.history.HistoryFragment
import com.localhost.taxiapp.presentation.feature.rideslist.RidesListFragment
import dagger.Subcomponent

@RideScope
@Subcomponent(modules = arrayOf(RideModule::class))
interface RideComponent {
    fun inject(ridesListFragment: RidesListFragment)

    fun inject(addRideActivity: AddRideActivity)

    fun inject(historyFragment: HistoryFragment)

    fun inject(activeRideFragment: ActiveRideFragment)

    @RideScope
    @Subcomponent.Builder
    interface Builder {
        @RideScope
        fun build(): RideComponent
    }
}
