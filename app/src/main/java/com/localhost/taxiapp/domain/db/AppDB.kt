package com.localhost.taxiapp.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.localhost.taxiapp.data.ride.Ride

@Database(entities = arrayOf(Ride::class), version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun rideDAO() : RideDAO
}
