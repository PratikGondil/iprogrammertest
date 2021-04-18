package com.iprogrammer

import android.app.Application
import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.iprogrammer.modules.userdatamodule.view.fragment.WheatherDetailsFragment
import com.iprogrammer.modules.userdatamodule.viewmodel.WheatherDataViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class WheatherDataTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var viewModel: WheatherDataViewModel

    @Mock
    private lateinit var mockedApplication: Application

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        launchFragmentInContainer<WheatherDetailsFragment>(
            fragmentArgs = null, // Bundle
            themeResId = R.style.AppTheme,
            factory = null // FragmentFactory
                                                     )
        Looper.getMainLooper()

        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        viewModel = WheatherDataViewModel(mockedApplication)
    }



    @Test
    fun checkValidData(){
        Espresso.onView(ViewMatchers.withId(R.id.tempText)).perform(
            ViewActions.replaceText("das"),
            ViewActions.closeSoftKeyboard()

                                                                              )
        Espresso.onView(ViewMatchers.withId(R.id.txtMaxTempText)).perform(
            ViewActions.replaceText("Msd"),
            ViewActions.closeSoftKeyboard()
                                                                                )
        Espresso.onView(ViewMatchers.withId(R.id.txtMinTempText)).perform(
            ViewActions.replaceText("sads"),
            ViewActions.closeSoftKeyboard()
                                                                                )
        Espresso.onView(ViewMatchers.withId(R.id.txtDayText)).perform(
            ViewActions.replaceText("sdsad"),
            ViewActions.closeSoftKeyboard()
                                                                                )

    }


}