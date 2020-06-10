package com.localhost.taxiapp.domain.stop

import com.localhost.taxiapp.R
import com.localhost.taxiapp.data.stop.Stop
import javax.inject.Inject

class StopModel @Inject constructor() {
    fun getStops(): List<Stop> =
            arrayListOf(
                    Stop("Двойка","Кремлевская 35", R.drawable.dvoika),
                    Stop("Ду","Академика Парина\nул.Деревни универсады", R.drawable.uv),
                    Stop("Бустан","ул. Профессора Нужина, 3", R.drawable.bustan)
            )
}
