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
import com.alife.vegan.ui.theme.*


@Composable
fun ProgresBarWithText(
    infoList: List<String>,
    nowValue: List<Int> = listOf<Int>(20, 90, 5, 10, 4),
    totalValue: List<Int> = listOf<Int>(100, 250, 67, 20, 10),
    colorList: List<Color> = listOf(
        Color_Alife_Green,
        Color_Alife_Graph_Color1,
        Color_Alife_Graph_Color2,
        Color_Alife_Graph_Color3,
        Color_Alife_Graph_Color4
    )
) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        infoList.mapIndexed { idx, it ->
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
                            .border(BorderStroke(1.dp, color = colorList[idx]))
                    ) {
                        drawRect(
                            color = colorList[idx],
                            size = Size(
                                size.width * nowValue[idx].toFloat() / totalValue[idx].toFloat(),
                                size.height // 얼마큼 채워져있는지 %로 표현
                            )
                        )
                    }
                }
            }
            Text(
                text = "${nowValue[idx]}g / ${totalValue[idx]}g",
                fontSize = 12.sp,
                color = Color(0xFF474957)
            )
        }
    }
}

