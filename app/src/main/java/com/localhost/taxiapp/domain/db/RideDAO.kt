package com.localhost.taxiapp.domain.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.localhost.taxiapp.data.ride.Ride
import io.reactivex.Single
import kotlinx.coroutines.Deferred

@Dao
interface RideDAO {

    @Query("select * from ride")
    fun getHistory(): List<Ride>

    @Insert
    fun insertRides(city: List<Ride>)

    @Query("delete from ride")
    fun deleteAll()
}
