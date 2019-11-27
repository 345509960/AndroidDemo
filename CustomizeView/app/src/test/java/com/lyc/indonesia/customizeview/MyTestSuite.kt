package com.lyc.indonesia.customizeview

import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class.java)
@LargeTest
class MyClassTest {
    @get:Rule
    val activityRule = ActivityTestRule(MyClass::class.java)

    @Test fun myClassMethod_ReturnsTrue() { ... }
}