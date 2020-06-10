package com.localhost.taxiapp.domain.user

import com.google.gson.Gson
import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.user.*
import com.localhost.taxiapp.domain.apiTaxi.TaxiApiService
import com.vk.sdk.api.*
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKList
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class UserModel(val taxiApiService: TaxiApiService) {

    var curUser: User? = null
    var curUserPic: String? = null

    fun setUser() {
        val vkRequest = VKRequest("account.getProfileInfo")
        vkRequest.executeWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {
                super.onComplete(response)
                val user = Gson().fromJson(response?.responseString, UserResponse::class.java)
                curUser = user.response
                val disposable = register().subscribeBy(onSuccess = {}, onError = {})
            }
        }
        )
        setUserPic()
    }

    fun register(): Single<PostRideResponse> =
        taxiApiService
            .register(
                UserForRegistration(
                    curUser?.first_name,
                    curUser?.last_name,
                    curUser?.status,
                    curUser?.screen_name
                )
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun setUserPic() {
        val yourRequest = VKApi.users()
            .get(VKParameters.from(VKApiConst.USER_IDS, curUser?.screen_name, VKApiConst.FIELDS, "photo_400_orig"))

        yourRequest.executeSyncWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {
                super.onComplete(response)
                val usersArray = response?.parsedModel as VKList<VKApiUser>
                for (userFull in usersArray) {
                    curUserPic = userFull.photo_400_orig
                }
            }
        })
    }

    fun getUserPic(screenName: String, listener: VKRequest.VKRequestListener) {
        val yourRequest = VKApi.users()
            .get(VKParameters.from(VKApiConst.USER_IDS, screenName, VKApiConst.FIELDS, "photo_400_orig"))
        yourRequest.executeSyncWithListener(listener)
    }

    fun convertForList(friends: List<UserForList>): ArrayList<UserForListWithPic> {
        val list = ArrayList<UserForListWithPic>()
        for (friend in friends)
            getUserPic(friend.screen_name, object : VKRequest.VKRequestListener() {
                override fun onComplete(response: VKResponse?) {
                    super.onComplete(response)
                    val usersArray = response?.parsedModel as VKList<VKApiUser>
                    for (userFull in usersArray) {
                        list.add(
                            UserForListWithPic(
                                friend.name,
                                friend.friend,
                                friend.screen_name,
                                userFull.photo_400_orig
                            )
                        )
                    }
                }
            })
        return list
    }

    fun addFriend(user: UserForListWithPic) =
        taxiApiService.addFriend(curUser?.screen_name, user.screen_name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getFriends(screenName: String): Single<List<UserForList>> =
        taxiApiService.getFriends(screenName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}
