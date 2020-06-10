package com.localhost.taxiapp.presentation.feature.stopslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.localhost.taxiapp.R
import com.localhost.taxiapp.data.stop.Stop

class StopsAdapter : RecyclerView.Adapter<StopsHolder>() {

    var rides: List<Stop> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopsHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.stops_list_item, parent, false)
        return StopsHolder(v)
    }

    override fun getItemCount(): Int = rides.size

    override fun onBindViewHolder(holder: StopsHolder, position: Int) = holder.bind(rides[position])
}
