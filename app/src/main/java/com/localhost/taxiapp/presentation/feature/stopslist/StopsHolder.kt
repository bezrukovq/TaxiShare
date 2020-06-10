package com.localhost.taxiapp.presentation.feature.stopslist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.localhost.taxiapp.data.stop.Stop
import kotlinx.android.synthetic.main.stops_list_item.view.*

class StopsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(stop: Stop) {
        itemView.cv_title.text = stop.name
        itemView.cv_description.text = stop.description
        itemView.cv_image.setImageResource(stop.pic)
    }
}
