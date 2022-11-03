package com.alife.vegan.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alife.vegan.ui.theme.Color_Alife_Green


@Composable
fun ProgresBarWithText(infoList: List<String>) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        infoList.map {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp),
                verticalAlignment = Alignment.CenterVertically
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
                                size.width * 0.7f, size.height // 얼마큼 채워져있는지 %로 표현
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

