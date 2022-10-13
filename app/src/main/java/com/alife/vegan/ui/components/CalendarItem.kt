package com.alife.vegan.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alife.vegan.ui.theme.Color_Alife_Cyan

@ExperimentalMaterialApi
@Composable
fun CalendarItem(isClicked: Boolean, date: Pair<String, Int>, onclick: () -> Unit) {
  val textColor = if (isClicked) Color.White else Color(0xff171930)
  val backgroundColor = if (isClicked) Color_Alife_Cyan else Color.White
  Surface(
    shape = RoundedCornerShape(15),
    border = BorderStroke(1.dp, Color(0xffE5E8EB)),
    color = backgroundColor,
    modifier = Modifier
      .width(50.dp)
      .clickable {
        onclick()
      }
  ) {
    Column(
      modifier = Modifier
        .padding(10.dp, 10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(text = date.first, fontSize = 11.sp, color = textColor)
      Text(
        text = date.second.toString(),
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        color = textColor
      )
    }
  }
}