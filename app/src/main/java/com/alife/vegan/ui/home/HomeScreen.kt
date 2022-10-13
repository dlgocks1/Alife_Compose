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

    KcalInfo(listOf("단백질", "탄수화물", "지방", "탄수화물", "나트륨"))
  }
}

@Composable
fun KcalInfo(infoList: List<String>) {
  Column(
    horizontalAlignment = Alignment.End,
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    infoList.map {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .height(18.dp)
      ) {
        Text(
          text = it,
          fontWeight = FontWeight.Bold,
          fontSize = 17.sp,
          modifier = Modifier.weight(0.3f)
        )
        Box(
          modifier = Modifier
            .fillMaxHeight()
            .weight(0.7f)
        ) {
          Canvas(
            modifier = Modifier
              .fillMaxSize()
              .border(BorderStroke(1.dp, color = Color_Alife_Green))
          ) {
            Log.i("test", size.toString())
            drawRect(
              color = Color_Alife_Green,
              size = Size(
                size.width * 0.7f, size.height
              )
            )
          }
        }
      }
      Text(
        text = "1250g / 7514g", fontSize = 12.sp, color = Color(0xFF474957)
      )
    }
  }
}


@Preview
@Composable
fun HomeScreenPreview() {
  HomeScreen()
}