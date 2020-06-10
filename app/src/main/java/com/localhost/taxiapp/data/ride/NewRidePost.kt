package com.localhost.taxiapp.data.ride

data class NewRidePost(
    val creatorId: String,
    val startPoint: String,
    val destPoint: String,
    val size: Int,
    val hours: Int,
    val minutes: Int
)
