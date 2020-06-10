package com.localhost.taxiapp.presentation.di.module

import com.localhost.taxiapp.domain.apiTaxi.TaxiApiService
import com.localhost.taxiapp.domain.user.UserModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserModule {

    @Provides
    @Singleton
    fun provideUserModel(taxiApiService: TaxiApiService): UserModel =
        UserModel(taxiApiService)
}
