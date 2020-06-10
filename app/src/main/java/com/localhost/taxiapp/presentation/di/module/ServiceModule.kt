package com.localhost.taxiapp.presentation.di.module

import com.localhost.taxiapp.domain.apiTaxi.TaxiApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideTaxiApiService(retrofit: Retrofit): TaxiApiService =
        retrofit.create(TaxiApiService::class.java)
}
