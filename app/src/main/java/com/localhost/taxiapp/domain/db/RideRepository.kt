package com.localhost.taxiapp.domain.db

import com.localhost.taxiapp.data.ride.Ride
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Deferred

class RideRepository(val rideDAO: RideDAO) {

    suspend fun deleteAll() =
        rideDAO.deleteAll()

    suspend fun getRides() =
        rideDAO.getHistory()

    suspend fun insertRides(ridesList: List<Ride>) =
         rideDAO.insertRides(ridesList)
}
