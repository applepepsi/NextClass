package com.example.nextclass.appComponent

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDateBottomSheet(
    onDismiss: () -> Unit,
    updateSelectData: (LocalDate)->Unit,

    ) {
    val modalBottomSheetState = rememberModalBottomSheetState()



    ModalBottomSheet(

        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        CustomDatePicker(

            updateSelectData = updateSelectData
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(

    updateSelectData: (LocalDate) -> Unit
) {
    val dateState = rememberDatePickerState()

    LaunchedEffect(dateState.selectedDateMillis) {
        dateState.selectedDateMillis?.let {
            val selectedDateTime = Instant.ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            updateSelectData(selectedDateTime)
        }
    }

    Column {
        DatePicker(
            state = dateState,
            title = {
                Text(text = "날짜 선택")
            },
            headline = {
                Text(text = "날짜를 선택해 주세요.")
            }
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectTimeBottomSheet(
    onDismiss: () -> Unit,
    updateSelectTime: (LocalTime) -> Unit

    ) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(

        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        CustomTimePicker(

            updateSelectTime=updateSelectTime
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePicker(
    updateSelectTime: (LocalTime) -> Unit
) {
    val timeState = rememberTimePickerState()

    LaunchedEffect(timeState.hour, timeState.minute) {
        val selectedTime = LocalTime.of(timeState.hour, timeState.minute)
        updateSelectTime(selectedTime)
    }

    Column {
        TimePicker(
            state = timeState,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectDatePreview() {
    CustomDatePicker() {

    }
}


@Preview(showBackground = true)
@Composable
fun SelectTimePreview() {
    CustomTimePicker(updateSelectTime = {})
        

}

