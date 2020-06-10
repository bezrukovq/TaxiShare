package com.localhost.taxiapp.presentation.feature.addride

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.localhost.taxiapp.R
import com.localhost.taxiapp.TaxiApplication
import com.localhost.taxiapp.data.ride.PostRideResponse
import kotlinx.android.synthetic.main.activity_add_ride.*
import javax.inject.Inject

class AddRideActivity : MvpAppCompatActivity(), AddRideView {

    @Inject
    @InjectPresenter
    lateinit var presenter: AddRidePresenter

    @ProvidePresenter
    fun initPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        TaxiApplication.rideComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ride)
        title = getString(R.string.create_new_ride)
        time_picker.setIs24HourView(true)
        btn_create.setOnClickListener { x -> postRide() }
    }

    override fun setUpSpinners(stopsList: Array<String>, placesList: Array<Int>) {
        val stopsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, stopsList)
        stopsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_startpoint.adapter = stopsAdapter
        sp_destpoint.adapter = stopsAdapter
        val placesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, placesList)
        placesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_places.adapter = placesAdapter
    }

    fun postRide() {
        btn_create.isClickable = false
        posting_progress.visibility = View.VISIBLE
        presenter.postRide(
            sp_startpoint.selectedItem as String,
            sp_destpoint.selectedItem as String,
            sp_places.selectedItem as Int,
            time_picker.hour,
            time_picker.minute
        )
    }

    override fun postResult(response: PostRideResponse) {
        if (response.successful) {
            Toast.makeText(this, getString(R.string.create_success), Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
            btn_create.isClickable = true
            posting_progress.visibility = View.GONE
        }
    }
}
