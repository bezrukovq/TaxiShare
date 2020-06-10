package com.localhost.taxiapp

import android.app.Application
import com.localhost.taxiapp.presentation.di.component.AppComponent
import com.localhost.taxiapp.presentation.di.component.DaggerAppComponent
import com.vk.sdk.VKSdk
import com.localhost.taxiapp.presentation.di.component.RideComponent
import com.localhost.taxiapp.presentation.di.module.MainModule

class TaxiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
        appComponent = DaggerAppComponent.builder().mainModule(MainModule(this)).build()
    }

    companion object {
        lateinit var appComponent: AppComponent
        var rideComponent: RideComponent? = null
            get() {
                if (field == null)
                    field = appComponent.rideComponent().build()
                return field
            }
    }
}
