package com.localhost.taxiapp.presentation.feature.history

import com.localhost.taxiapp.data.ride.ActionRidePost
import com.localhost.taxiapp.data.ride.Ride
import com.localhost.taxiapp.domain.apiTaxi.TaxiApiService
import com.localhost.taxiapp.domain.db.RideRepository
import com.localhost.taxiapp.domain.ride.RideModel
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCaseOrder
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class RideModelTest : StringSpec({
    val taxiApiService: TaxiApiService = mockk(relaxed = true)
    val rideRepository: RideRepository = mockk(relaxed = true)
    var rideModel = RideModel(taxiApiService, rideRepository)
    val mainThreadSurrogate = newSingleThreadContext("UI thread")
    testOrder = TestCaseOrder.Sequential
    val testName = "vova"

    beforeTest {
        clearAllMocks()
        rideModel = RideModel(taxiApiService, rideRepository)
        Dispatchers.setMain(mainThreadSurrogate)
    }
    afterTest {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        clearAllMocks()
    }

    "getCurrRide case" {
        rideModel.getCurrRide(testName)

        coVerify { taxiApiService.getCurrRide(testName) }
    }

    "rideAction case" {
        val rideID = 1
        val screenName = "abc"
        val action = "cancel"
        rideModel.rideAction(rideID,screenName,action)

        coVerify { taxiApiService.finish(ActionRidePost(action,rideID,screenName)) }
    }

    "save history case"{
        val mockRide: Ride = mockk()
        val mockList = ArrayList<Ride>().apply { add(mockRide) }
        rideModel.saveHistory(mockList)
        coVerify {
            rideRepository.deleteAll()
            rideRepository.insertRides(mockList)
        }
    }
})