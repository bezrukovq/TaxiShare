package com.localhost.taxiapp.domain.apiTaxi

import com.localhost.taxiapp.data.ride.ActionRidePost
import com.localhost.taxiapp.data.ride.NewRidePost
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.ride.Ride
import com.localhost.taxiapp.data.user.UserForList
import com.localhost.taxiapp.data.user.UserForRegistration
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TaxiApiService {
    @GET("ride/getRides")
    fun getRidesList(): Single<List<Ride>>

    @GET("ride/getRide")
    fun getCurrRide(@Query("username") username: String?): Single<Ride>

    @GET("ride/getPassengers")
    fun getPassengers(@Query("rideId") rideId:Int,@Query("userId") userId:String): Single<List<UserForList>>

    @GET("ride/getHistory")
    fun getHistory(@Query("username") username:String): Single<List<Ride>>

    @POST("ride/create")
    fun postRide(@Body newRidePost: NewRidePost): Single<PostRideResponse>

    @POST("user/create")
    fun register(@Body user: UserForRegistration?) : Single<PostRideResponse>

    @GET("user/getFriends")
    fun getFriends(@Query("screen_name") screenName:String): Single<List<UserForList>>

    @POST("ride/action")
    fun finish(@Body actionRidePost: ActionRidePost): Single<PostRideResponse>

    @GET("user/addFriend")
    fun addFriend(
            @Query("user")screen_name: String?,
            @Query("friend") screen_name1: String
    ): Single<PostRideResponse>

}
