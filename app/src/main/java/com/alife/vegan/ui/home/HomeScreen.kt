package com.alife.vegan.ui.home

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alife.vegan.ui.calendar.CalendarViewModel
import com.alife.vegan.ui.components.KcalProgressBar
import com.alife.vegan.ui.components.ProgresBarWithText

@Composable
fun HomeScreen(
    calendarViewModel: CalendarViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val nowKcal = calendarViewModel.dietList.value?.sumOf { it.calory }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 20.dp)
            .scrollable(state = scrollState, orientation = Orientation.Vertical),
    ) {
        Text(text = "일일 칼로리", fontSize = 27.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        if (nowKcal != null) {
            KcalProgressBar(nowKcal, (nowKcal / 2139 * 360).toFloat())
        }
        Spacer(modifier = Modifier.height(20.dp))

        ProgresBarWithText(listOf("단백질", "탄수화물", "지방", "탄수화물", "나트륨"))
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}