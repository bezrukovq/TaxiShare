package com.localhost.taxiapp.presentation.feature.history

import com.localhost.taxiapp.data.ride.PostRideResponse
import com.localhost.taxiapp.domain.ride.RideModel
import com.localhost.taxiapp.domain.user.UserModel
import com.localhost.taxiapp.presentation.feature.addride.AddRidePresenter
import com.localhost.taxiapp.presentation.feature.addride.`AddRideView$$State`
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
class AddRidePresenterTest : StringSpec({
    val rideModel: RideModel = mockk(relaxed = true)
    val userModel: UserModel = mockk()
    val viewState = mockk<`AddRideView$$State`>(relaxUnitFun = true)
    var addRidePresenter = AddRidePresenter(rideModel, userModel)
    val mainThreadSurrogate = newSingleThreadContext("UI thread")
    testOrder = TestCaseOrder.Sequential
    val testName = "vova"

    beforeTest {
        clearAllMocks()
        addRidePresenter = AddRidePresenter(rideModel, userModel)
        addRidePresenter.setViewState(viewState)
        Dispatchers.setMain(mainThreadSurrogate)

    }
    afterTest {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        clearAllMocks()
    }

    "ride add negative case" {
        val message = "message"
        val mockNegativeResult = PostRideResponse(false, message)
        coEvery { rideModel.postRide(any()) } throws Throwable(message)
        every { userModel.curUser } returns mockk {
            every { screen_name } returns testName
        }

        addRidePresenter.postRide("", "", 0, 0, 0)

        verify { viewState.postResult(mockNegativeResult) }
    }

    "ride add positive case" {
        val mockResult: PostRideResponse = mockk()
        coEvery { rideModel.postRide(any()) } returns mockResult
        every { userModel.curUser } returns mockk {
            every { screen_name } returns testName
        }

        addRidePresenter.postRide("", "", 0, 0, 0)

        verify { viewState.postResult(mockResult) }
    }
})