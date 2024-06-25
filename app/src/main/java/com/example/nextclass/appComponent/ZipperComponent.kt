package com.example.nextclass.appComponent


import android.content.res.Resources
import androidx.compose.foundation.layout.Column

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter

import android.view.MotionEvent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.TimeTableViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AccreditationCalculationComponent(
    updateLayoutHeight: (initialHeight: Int, initialTouchY: Float, currentTouchY: Float, screenHeight: Int) -> Unit
) {
    val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    var initialTouchY by remember { mutableStateOf(0f) }
    var initialHeight by remember { mutableStateOf(0) }


    Column(modifier = Modifier
        .pointerInteropFilter {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialTouchY = it.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    updateLayoutHeight(initialHeight, initialTouchY, it.rawY, screenHeight)
                }
                MotionEvent.ACTION_UP -> {

                }
            }
            true
        }
    ) {
        TimeTableComponent(
            classDataList = sampleEvents,
            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->

                initialHeight = layoutCoordinates.size.height

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ZipperPreview() {

//    val mainNavController= rememberNavController()
//    val testRepository = TestRepository()
//    val loginViewModel = LoginViewModel(testRepository)
    val timeTableViewModel = TimeTableViewModel()
    NextClassTheme {
        AccreditationCalculationComponent(
            updateLayoutHeight = { initialHeight, initialTouchY, currentTouchY, screenHeight ->
                timeTableViewModel.updateLayoutHeight(initialHeight, initialTouchY, currentTouchY, screenHeight)
            }
        )

    }
}

