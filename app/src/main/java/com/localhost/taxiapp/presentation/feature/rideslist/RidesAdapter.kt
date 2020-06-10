package com.localhost.taxiapp.presentation.feature.rideslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.localhost.taxiapp.R
import com.localhost.taxiapp.data.ride.Ride

class RidesAdapter(val joinToRideCallback: JoinRideCallback) : RecyclerView.Adapter<RidesHolder>() {

    var rides: List<Ride> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RidesHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rides_list_item, parent, false)
        return RidesHolder(v)
    }

    override fun getItemCount(): Int = rides.size

    override fun onBindViewHolder(holder: RidesHolder, position: Int) =
        holder.bind(rides[position],joinToRideCallback)
}
