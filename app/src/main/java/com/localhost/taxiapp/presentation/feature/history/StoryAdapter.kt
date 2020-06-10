package com.localhost.taxiapp.presentation.feature.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.localhost.taxiapp.R
import com.localhost.taxiapp.data.ride.Ride

class StoryAdapter : RecyclerView.Adapter<StoryHolder>() {
    var rides: List<Ride> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rides_history_item, parent, false)
        return StoryHolder(v)
    }

    override fun getItemCount(): Int = rides.size

    override fun onBindViewHolder(holder: StoryHolder, position: Int) = holder.bind(rides[position])
}
