package com.localhost.taxiapp.presentation.feature.history

import com.localhost.taxiapp.data.ride.Ride
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
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
class HistoryPresenterTest : StringSpec({
    val rideModel: RideModel = mockk(relaxed = true)
    val userModel: UserModel = mockk()
    val viewState = mockk<`HistoryView$$State`>(relaxUnitFun = true)
    var historyPresenter = HistoryPresenter(rideModel, userModel)
    val mainThreadSurrogate = newSingleThreadContext("UI thread")
    testOrder = TestCaseOrder.Sequential
    val testName = "vova"

    beforeTest {
        clearAllMocks()
        historyPresenter = HistoryPresenter(rideModel, userModel)
        historyPresenter.setViewState(viewState)
        Dispatchers.setMain(mainThreadSurrogate)

    }
    afterTest {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        clearAllMocks()
    }

    "refresh negative case" {
        val mockRide: Ride = mockk()
        val mockList = ArrayList<Ride>().apply { add(mockRide) }
        every { userModel.curUser } returns mockk {
            every { screen_name } returns testName
        }
        coEvery { rideModel.getHistoryDB() } returns mockList
        coEvery { rideModel.getHistory(testName) } throws Throwable()

        historyPresenter.refresh()

        verify { viewState.setList(mockList) }
    }

    "refresh positive case" {
        val mockRide: Ride = mockk()
        val mockList = ArrayList<Ride>().apply { add(mockRide) }
        coEvery { rideModel.saveHistory(listOf()) } answers {}
        every { userModel.curUser } returns mockk {
            every { screen_name } returns testName
        }
        coEvery { rideModel.getHistory(testName) } returns mockList

        historyPresenter.refresh()

        verify { viewState.setList(mockList) }
    }
})