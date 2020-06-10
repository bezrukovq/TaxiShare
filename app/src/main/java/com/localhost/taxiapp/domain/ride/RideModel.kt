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
import kotlinx.coroutines.Deferred

class RideModel(val taxiApiService: TaxiApiService, val rideRepository: RideRepository) {

    var stopsList = arrayOf("Ду", "Двойка", "Бустан")
    var placesList = arrayOf(1, 2, 3, 4, 5, 6, 7)

    suspend fun getCurrRide(userId: String?): Ride =
        taxiApiService.getCurrRide(userId)

    suspend fun getPassengers(rideId: Int, screen_name: String): List<UserForList> =
        taxiApiService.getPassengers(rideId, screen_name)

    suspend fun getRidesList(): List<Ride> =
        taxiApiService.getRidesList()

    suspend fun getHistory(username: String): List<Ride> =
        taxiApiService.getHistory(username)


    suspend fun postRide(newRidePost: NewRidePost): PostRideResponse =
        taxiApiService.postRide(newRidePost)

    suspend fun rideAction(rideId: Int, screen_name: String?, action: String): PostRideResponse =
        taxiApiService.finish(ActionRidePost(action, rideId, screen_name.toString()))


    suspend fun saveHistory(ridesList: List<Ride>) {
        rideRepository.deleteAll()
        rideRepository.insertRides(ridesList)
    }

    suspend fun getHistoryDB(): List<Ride> =
        rideRepository.getRides()
}
