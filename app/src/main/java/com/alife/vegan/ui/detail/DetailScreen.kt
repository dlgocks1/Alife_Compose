package com.alife.vegan.ui.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alife.vegan.R
import com.alife.vegan.network.response.GetFoodByPriceResponseItem
import com.alife.vegan.ui.theme.Color_Alife_Cyan
import com.alife.vegan.ui.theme.Color_Alife_Green
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        GlideImage(
            imageModel = R.drawable.img_dummy,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("짜장면", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_bookmark_border_24),
                    contentDescription = null
                )
            }
            Text(text = "음식 설명을 해주는 텍스트입니다.", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(20.dp))
            SubTitle("영양정보")
            Column(modifier = Modifier.padding(10.dp, 10.dp)) {
                CustomProgressBar(listOf("단백질", "칼로리", "지방"))
            }
            SubTitle("재료")
            Row {

            }
            SubTitle("레시피")
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(text = "1. 야채를 다듬는다.", fontSize = 16.sp)
                Text(text = "2. 춘장을 볶는다.", fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun SubTitle(str: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(0.dp, 10.dp)
    ) {
        Canvas(modifier = Modifier.size(12.dp)) {
            drawCircle(color = Color_Alife_Cyan)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = str, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}


@Composable
private fun CustomProgressBar(
    infoList: List<String>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        infoList.map {
            Column(horizontalAlignment = Alignment.End) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier.weight(0.3f),
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
                            drawRect(
                                color = Color_Alife_Green,
                                size = Size(
                                    size.width * 0.7f,
                                    size.height // 얼마큼 채워져있는지 %로 표현
                                ),
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "123/123",
                    fontSize = 12.sp,
                    color = Color(0xFF474957)
                )
            }
        }
    }
}