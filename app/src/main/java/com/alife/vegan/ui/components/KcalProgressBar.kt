package com.alife.vegan.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KcalProgressBar(nowKcal: Double, targetvalue: Float) {
    val animatedValue = remember { Animatable(0f) }

    // 특정 값으로 색을 채우는 Animation
    LaunchedEffect(Unit) {
        animatedValue.animateTo(
            targetValue = targetvalue,
            animationSpec = tween(durationMillis = 2000, easing = LinearEasing),
        )
    }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val sizeArc = size / 1.75F
                drawArc(
                    color = Color(0xFFE1E2E9),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    topLeft = Offset(
                        (size.width - sizeArc.width) / 2f,
                        (size.height - sizeArc.height) / 2f
                    ),
                    size = sizeArc,
                    style = Stroke(width = 70f)
                )

                drawArc(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xff63C6C4), Color(0xff97CA49)
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite,
                    ),
                    startAngle = 100f,
                    sweepAngle = animatedValue.value,
                    useCenter = false,
                    topLeft = Offset(
                        (size.width - sizeArc.width) / 2f,
                        (size.height - sizeArc.height) / 2f
                    ),
                    size = sizeArc,
                    style = Stroke(width = 70f, cap = StrokeCap.Round)
                )

            }
            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "${nowKcal}Kcal", fontSize = 23.sp, fontWeight = FontWeight.Bold)
                Text(text = "2139Kcal/day", fontSize = 12.sp, color = Color(0xFF474957))
            }
        }
    }
}