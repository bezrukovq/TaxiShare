package com.localhost.taxiapp.domain.ride

import com.localhost.taxiapp.data.ride.ActionRidePost
import com.localhost.taxiapp.data.ride.NewRidePost
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.ride.Ride
import com.localhost.taxiapp.data.user.UserForList
import com.localhost.taxiapp.domain.apiTaxi.TaxiApiService
import com.localhost.taxiapp.domain.db.RideRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class RideModel(val taxiApiService: TaxiApiService, val rideRepository: RideRepository) {

    var stopsList = arrayOf("Ду", "Двойка", "Бустан")
    var placesList = arrayOf(1, 2, 3, 4, 5, 6, 7)

    fun getCurrRide(userId: String?): Single<Ride> =
        taxiApiService.getCurrRide(userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getPassengers(rideId: Int, screen_name: String): Single<List<UserForList>> =
        taxiApiService.getPassengers(rideId, screen_name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getRidesList(): Single<List<Ride>> =
        taxiApiService.getRidesList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getHistory(username: String): Single<List<Ride>> =
        taxiApiService.getHistory(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun postRide(newRidePost: NewRidePost): Single<PostRideResponse> =
        taxiApiService.postRide(newRidePost)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun rideAction(rideId: Int, screen_name: String?, action: String): Single<PostRideResponse> =
        taxiApiService.finish(ActionRidePost(action, rideId, screen_name.toString()))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun saveHistory(ridesList: List<Ride>) {
        rideRepository.deleteAll().subscribeBy(
            onComplete = {
                rideRepository.insertRides(ridesList)
            },
            onError = {})
    }

    fun getHistoryDB(): Single<List<Ride>> =
        rideRepository.getRides()
}
