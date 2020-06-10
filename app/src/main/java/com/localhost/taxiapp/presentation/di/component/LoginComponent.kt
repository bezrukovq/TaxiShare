package com.localhost.taxiapp.presentation.di.component

import com.localhost.taxiapp.presentation.feature.login.LoginActivity
import dagger.Subcomponent

@Subcomponent
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): LoginComponent
    }
}
