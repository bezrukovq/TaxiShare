package com.localhost.taxiapp.presentation.di.module

import com.localhost.taxiapp.domain.apiTaxi.TaxiApiService
import com.localhost.taxiapp.domain.db.RideRepository
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.presentation.di.RideScope
import dagger.Module
import dagger.Provides

@RideScope
@Module
class RideModule {
    @RideScope
    @Provides
    fun provideRideModel(taxiApiService: TaxiApiService, rideRepository: RideRepository): RideModel =
        RideModel(taxiApiService,rideRepository)
}
