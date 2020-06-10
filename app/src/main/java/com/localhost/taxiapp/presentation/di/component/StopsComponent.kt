package com.localhost.taxiapp.presentation.di.component

import com.localhost.taxiapp.presentation.feature.stopslist.StopsListFragment
import dagger.Subcomponent

@Subcomponent
interface StopsComponent {

    fun inject(stopsListFragment: StopsListFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): StopsComponent
    }
}
