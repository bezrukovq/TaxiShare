package com.localhost.taxiapp.presentation.feature.userlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.localhost.taxiapp.data.user.UserForListWithPic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.users_list_item.view.*

class UsersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(user: UserForListWithPic, openProfileCallback: OpenProfileCallback) {
        Picasso.get().load(user.picURL).into(itemView.iv_icon)
        itemView.tv_name.text = user.name
        itemView.setOnClickListener { x ->
            openProfileCallback.open(user, itemView.tv_name, itemView.iv_icon)
        }
    }
}
