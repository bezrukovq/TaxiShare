package com.localhost.taxiapp.domain.apiVk

import com.localhost.taxiapp.data.user.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface VKApiService {
    @GET("method/account.getProfileInfo")
    fun getCurUser(@Query("access_token") access_token: String, @Query("v") v: String): Single<UserResponse>

}
