package com.localhost.taxiapp.domain.db

import com.localhost.taxiapp.data.ride.Ride
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RideRepository(val rideDAO: RideDAO) {

    fun deleteAll() =
        Completable.fromAction { rideDAO.deleteAll() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getRides(): Single<List<Ride>> =
        rideDAO.getHistory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun insertRides(ridesList: List<Ride>) =
        Completable.fromAction { rideDAO.insertRides(ridesList) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()
}
