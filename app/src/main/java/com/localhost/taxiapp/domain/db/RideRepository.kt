package com.localhost.taxiapp.domain.db

import com.localhost.taxiapp.data.ride.Ride
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Deferred

class RideRepository(val rideDAO: RideDAO) {

    fun deleteAll() =
        rideDAO.deleteAll()

    fun getRides() =
        rideDAO.getHistory()

    fun insertRides(ridesList: List<Ride>) =
         rideDAO.insertRides(ridesList)
}
