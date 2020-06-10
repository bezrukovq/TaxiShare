package com.localhost.taxiapp.presentation.feature.history

import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.data.ride.Ride
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
import com.localhost.taxiapp.presentation.feature.activeride.ActiveRidePresenter
import com.localhost.taxiapp.presentation.feature.activeride.`ActiveRideView$$State`
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class ActiveRidePresenterJunitTest() {
    var rideModel: RideModel = mockk()
    var userModel: UserModel = mockk()
    val viewState = mockk<`ActiveRideView$$State`>(relaxUnitFun = true)
    var activeRidePresenter = ActiveRidePresenter(rideModel, userModel)
    val testName = "vova"
    @ObsoleteCoroutinesApi
    val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeEach
    fun setUp() {
        clearAllMocks()
        activeRidePresenter = ActiveRidePresenter(rideModel, userModel)
        activeRidePresenter.setViewState(viewState)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        clearAllMocks()
    }

    @Test
    @DisplayName("ride action negative case")
    fun rideActionNeg() {
        coEvery { rideModel.rideAction(1, any(), any()) } throws Throwable()

        activeRidePresenter.rideAction(1, "")

        verify { viewState.showError(any()) }
    }

    @Test
    @DisplayName("ride action positive case")
    fun rideActionPos() {
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

    @Test
    @DisplayName("refresh negative case")
    fun refreshActionNeg() {
        every { userModel.curUser } returns mockk {
            every { screen_name } returns testName
        }
        coEvery { rideModel.getCurrRide(any()) } throws Throwable()

        activeRidePresenter.refresh()

        verify { viewState.showError(any()) }
    }

    @Test
    @DisplayName("refresh positive case")
    fun refreshActionPos() {
        val mockRide: Ride = Ride(5, "", "", "", "", 1, false)
        coEvery { rideModel.saveHistory(listOf()) } answers {}
        every { userModel.curUser } returns mockk {
            every { screen_name } returns testName
        }
        coEvery { rideModel.getCurrRide(testName) } returns mockRide

        activeRidePresenter.refresh()

        verify { viewState.setRide(mockRide) }
    }
}