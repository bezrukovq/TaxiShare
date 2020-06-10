package com.localhost.taxiapp.presentation.feature.history

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.localhost.taxiapp.data.ride.Ride
import kotlinx.android.synthetic.main.rides_history_item.view.*

class StoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(ride: Ride) {
        itemView.tv_startpoint.text = ride.startPoint
        itemView.tv_destpoint.text = ride.destPoint
        itemView.tv_time.text = ride.time
        itemView.tv_date.text = ride.date
    }
}
