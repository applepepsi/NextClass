package com.example.nextclass.appComponent

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ehsanmsz.mszprogressindicator.progressindicator.BallClipRotateProgressIndicator
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red

@Composable
fun ProgressBarComponent(
    state:Boolean=false
) {
    Log.d("State", state.toString())

    if (state) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(alpha = 0.1f))
                .clickable(enabled = false) {},
            contentAlignment = Alignment.Center
        ) {
            BallClipRotateProgressIndicator(
                modifier = Modifier,
                maxDiameter = 100.dp,
                minDiameter = 100.dp,
                color = Pastel_Red,
                animationDuration = 800,
                strokeWidth = 5.dp,
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ProgressBarPreview() {


    NextClassTheme {
        ProgressBarComponent()
    }
}