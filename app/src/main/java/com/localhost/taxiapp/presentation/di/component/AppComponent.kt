package com.localhost.taxiapp.presentation.di.component

import com.localhost.taxiapp.presentation.di.module.*
import com.localhost.taxiapp.presentation.feature.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DBModule::class,UserModule::class,MainModule::class, NetModule::class, ServiceModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun loginComponent(): LoginComponent.Builder

    fun rideComponent(): RideComponent.Builder

    fun userComponent(): UserComponent.Builder

    fun stopsComponent(): StopsComponent.Builder

    fun friendsComponent(): FriendsComponent.Builder
}
