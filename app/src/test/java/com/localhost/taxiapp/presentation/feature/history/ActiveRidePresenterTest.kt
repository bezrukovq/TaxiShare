package com.localhost.taxiapp.presentation.feature.history

import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.ride.Ride
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
import com.localhost.taxiapp.presentation.feature.activeride.ActiveRidePresenter
import com.localhost.taxiapp.presentation.feature.activeride.`ActiveRideView$$State`
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCaseOrder
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class ActiveRidePresenterTest : StringSpec({
    val rideModel: RideModel = mockk()
    val userModel: UserModel = mockk()
    val viewState = mockk<`ActiveRideView$$State`>(relaxUnitFun = true)
    var activeRidePresenter = ActiveRidePresenter(rideModel, userModel)
    val mainThreadSurrogate = newSingleThreadContext("UI thread")
    testOrder = TestCaseOrder.Sequential
    val testName = "vova"

    beforeTest {
        clearAllMocks()
        activeRidePresenter = ActiveRidePresenter(rideModel, userModel)
        activeRidePresenter.setViewState(viewState)
        Dispatchers.setMain(mainThreadSurrogate)

    }
    afterTest {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        clearAllMocks()
    }

    "ride action negative case" {
        coEvery { rideModel.rideAction(any(), any(), any()) } throws Throwable()

        activeRidePresenter.rideAction(1, "")

        verify { viewState.showError(any()) }
    }

    "ride action positive case" {
        val postRideResponse: PostRideResponse = mockk()
        coEvery { rideModel.rideAction(2, any(), any()) } returns postRideResponse
        every { userModel.curUser } returns mockk {
            every { screen_name } returns ""
        }

        activeRidePresenter.rideAction(2, "")

        verify {
            viewState.finish(postRideResponse)
        }
    }

    "refresh negative case" {
        every { userModel.curUser } returns mockk {
            every { screen_name } returns testName
        }
        coEvery { rideModel.getCurrRide(any()) } throws Throwable()

        activeRidePresenter.refresh()

        verify { viewState.showError(any()) }
    }

    "refresh positive case" {
        val mockRide = Ride(5, "", "", "", "", 1, false)
        coEvery { rideModel.saveHistory(listOf()) } answers {}
        every { userModel.curUser } returns mockk {
            every { screen_name } returns testName
        }
        coEvery { rideModel.getCurrRide(testName) } returns mockRide

        activeRidePresenter.refresh()

        verify { viewState.setRide(mockRide) }
    }
})