package com.localhost.taxiapp.presentation.feature.rideslist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.localhost.taxiapp.R
import com.localhost.taxiapp.data.ride.Ride
import kotlinx.android.synthetic.main.rides_list_item.view.*

class RidesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(ride: Ride, joinToRideCallback: JoinRideCallback) {
        itemView.tv_startpoint.text = ride.startPoint
        itemView.tv_destpoint.text = ride.destPoint
        itemView.tv_time.text = ride.time
        itemView.tv_places_count.text = ride.places.toString()
        itemView.tv_places_text.text = itemView.context.resources.getQuantityString(R.plurals.places, ride.places)
        itemView.btn_join.setOnClickListener { joinToRideCallback.join(ride.id) }
    }
}
