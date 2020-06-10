package com.localhost.taxiapp.domain.apiTaxi

import com.localhost.taxiapp.data.ride.ActionRidePost
import com.localhost.taxiapp.data.ride.NewRidePost
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.ride.Ride
import com.localhost.taxiapp.data.user.UserForList
import com.localhost.taxiapp.data.user.UserForRegistration
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TaxiApiService {
    @GET("ride/getRides")
    suspend fun getRidesList(): List<Ride>

    @GET("ride/getRide")
    suspend fun getCurrRide(@Query("username") username: String?): Ride

    @GET("ride/getPassengers")
    suspend fun getPassengers(@Query("rideId") rideId:Int,@Query("userId") userId:String): List<UserForList>

    @GET("ride/getHistory")
    suspend fun getHistory(@Query("username") username:String): List<Ride>

    @POST("ride/create")
    suspend fun postRide(@Body newRidePost: NewRidePost): PostRideResponse

    @POST("user/create")
    suspend fun register(@Body user: UserForRegistration?) : PostRideResponse

    @GET("user/getFriends")
    suspend fun getFriends(@Query("screen_name") screenName:String): List<UserForList>

    @POST("ride/action")
    suspend fun finish(@Body actionRidePost: ActionRidePost): PostRideResponse

    @GET("user/addFriend")
    suspend fun addFriend(
            @Query("user")screen_name: String?,
            @Query("friend") screen_name1: String
    ): PostRideResponse

}
