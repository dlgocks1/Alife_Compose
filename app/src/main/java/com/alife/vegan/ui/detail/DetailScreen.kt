package com.alife.vegan.ui.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alife.vegan.R
import com.alife.vegan.ui.theme.Color_Alife_Cyan
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreen() {
    val ingredient =
        "우리밀백밀가루,양파,양배추,대파,대두단백,무말랭이,당면,피망,조미유30,대두유,두부,참기름,감자전분,마늘,사고믹스-N,정제소금,설탕,혼합간장,감미유-S,간생강,혼합야채분말,후추가루"

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        GlideImage(
            imageModel = "http://43.201.114.241:8080/media/%EC%A7%84%EC%84%A0%ED%91%B8%EB%93%9C_%EC%9A%B0%EB%A6%AC%EB%B0%80%EC%95%BC%EC%B1%84%EC%86%90%EB%A7%8C%EB%91%90.jpg",
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
                Text("우리밀야채손만두", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_bookmark_border_24),
                    contentDescription = null
                )
            }
            Text(text = "진선푸드", fontSize = 12.sp)
            Text(text = "12,000원", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(20.dp))
            SubTitle("영양정보")
            Column(modifier = Modifier.padding(10.dp, 10.dp)) {
                CustomProgressBar(
                    listOf("칼로리", "단백질", "지방", "탄수화물"),
                    listOf("179kcal", "6g", "6g", "26g")
                )
            }
            SubTitle("재료")
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                ingredient.split(",").take(8).mapIndexed { index, s ->
                    Text(text = "${index + 1}. $s", fontSize = 16.sp)
                }
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
    infoList: List<String>,
    ingredientList: List<String>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        infoList.mapIndexed { idx, it ->
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
                    Column(
                        modifier = Modifier.weight(0.7f),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = ingredientList[idx],
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                        )
                    }

                }
            }
        }
    }
}