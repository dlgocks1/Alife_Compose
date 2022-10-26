package com.alife.vegan.ui.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alife.vegan.ui.components.KcalProgressBar
import com.alife.vegan.ui.components.ProgresBarWithText
import com.alife.vegan.ui.theme.Color_Alife_Green

@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp, 20.dp)
          .scrollable(state = scrollState, orientation = Orientation.Vertical),
    ) {
        Text(text = "일일 칼로리", fontSize = 27.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        KcalProgressBar(280f)
        Spacer(modifier = Modifier.height(20.dp))

        ProgresBarWithText(listOf("단백질", "탄수화물", "지방", "탄수화물", "나트륨"))
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}