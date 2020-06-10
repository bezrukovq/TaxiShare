package com.localhost.taxiapp.presentation.di.component

import com.localhost.taxiapp.presentation.di.FriendsScope
import com.localhost.taxiapp.presentation.feature.friendsList.FriendsActivity
import com.localhost.taxiapp.presentation.feature.otherprofile.OtherProfileActivity
import dagger.Subcomponent

@FriendsScope
@Subcomponent
interface FriendsComponent {

    fun inject(friendsActivity: FriendsActivity)

    fun inject(otherProfileActivity: OtherProfileActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): FriendsComponent
    }
}
