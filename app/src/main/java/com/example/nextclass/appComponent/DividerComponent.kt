package com.example.nextclass.appComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DividerComponent(
    dividerColor: Color=Color.White,
    modifier: Modifier
) {
    Divider(
        color = dividerColor,
        modifier = modifier

            .fillMaxWidth()

            .background(Color.White)
    )
}
