package com.localhost.taxiapp.data.ride

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ride")
data class Ride(
    @PrimaryKey
    val id: Int,
    val startPoint: String,
    val destPoint: String,
    val time: String,
    val date: String,
    var places: Int,
    val finished: Boolean
)
