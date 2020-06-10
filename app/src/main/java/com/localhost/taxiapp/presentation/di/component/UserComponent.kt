package com.localhost.taxiapp.presentation.di.component

import com.localhost.taxiapp.presentation.di.UserScope
import com.localhost.taxiapp.presentation.feature.myprofile.MyProfileFragment
import dagger.Subcomponent

@UserScope
@Subcomponent
interface UserComponent {

    fun inject(myProfileFragment: MyProfileFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): UserComponent
    }
}
