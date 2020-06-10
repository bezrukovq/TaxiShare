package com.localhost.taxiapp.presentation.feature.history

import com.localhost.taxiapp.domain.user.UserModel
import com.localhost.taxiapp.presentation.feature.myprofile.MyProfilePresenter
import com.localhost.taxiapp.presentation.feature.myprofile.`MyProfileView$$State`
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
class MyProfilePresenterTest : StringSpec({
    val userModel: UserModel = mockk()
    val viewState = mockk<`MyProfileView$$State`>(relaxUnitFun = true)
    var myProfilePresenter = MyProfilePresenter(userModel)
    val mainThreadSurrogate = newSingleThreadContext("UI thread")
    testOrder = TestCaseOrder.Sequential
    val testName = "vova"

    beforeTest {
        clearAllMocks()
        myProfilePresenter = MyProfilePresenter(userModel)
        myProfilePresenter.setViewState(viewState)
        Dispatchers.setMain(mainThreadSurrogate)

    }
    afterTest {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        clearAllMocks()
    }

    "openVK case" {
        every { userModel.curUser } returns mockk {
            every { screen_name } returns testName
        }

        myProfilePresenter.openVK()

        verify { viewState.openVK(testName) }
    }

    "open friends case" {
        every { userModel.curUser } returns mockk {
            every { screen_name } returns testName
        }

        myProfilePresenter.openFriends()

        verify { viewState.openFriends(testName) }
    }
})