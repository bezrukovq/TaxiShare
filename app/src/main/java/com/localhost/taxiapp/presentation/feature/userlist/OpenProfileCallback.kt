package com.localhost.taxiapp.presentation.feature.userlist

import android.view.View
import com.localhost.taxiapp.data.user.UserForListWithPic

interface OpenProfileCallback {
    fun open(userForListWithPic: UserForListWithPic,textView: View,imageView:View)
}
