package com.localhost.taxiapp.presentation.feature.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.localhost.taxiapp.R
import com.localhost.taxiapp.data.user.UserForListWithPic

class UsersAdapter(val openProfileCallback: OpenProfileCallback): RecyclerView.Adapter<UsersHolder>() {
    var users: List<UserForListWithPic> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.users_list_item, parent, false)
        return UsersHolder(v)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UsersHolder, position: Int) =
        holder.bind(users[position],openProfileCallback)
}
