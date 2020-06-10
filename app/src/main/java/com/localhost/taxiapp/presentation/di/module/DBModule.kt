package com.localhost.taxiapp.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.localhost.taxiapp.domain.db.AppDB
import com.localhost.taxiapp.domain.db.RideDAO
import com.localhost.taxiapp.domain.db.RideRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Provides
    @Singleton
    fun provideRideDao(appDB: AppDB): RideDAO =
        appDB.rideDAO()

    @Provides
    @Singleton
    fun provideDB(app: Context): AppDB = Room.databaseBuilder(app, AppDB::class.java, "database").build()

    @Provides
    @Singleton
    fun provideDateRepository(rideDao: RideDAO) = RideRepository(rideDao)
}
