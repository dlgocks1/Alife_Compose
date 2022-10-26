package com.alife.vegan.ui.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alife.vegan.R
import com.alife.vegan.ui.components.FoodItem
import com.alife.vegan.ui.components.ProgresBarWithText
import com.alife.vegan.ui.components.TitleText
import com.alife.vegan.ui.registerDiet.RegisterDietViewModel
import com.alife.vegan.ui.theme.Color_Alif_Gray
import com.alife.vegan.ui.theme.Color_Alif_GrayBackground
import com.alife.vegan.ui.theme.Color_Alife_Cyan

@Composable
fun RegisterRecDietFoodScreen(
    navController: NavController = rememberNavController(),
    registerDietViewModel: RegisterDietViewModel = hiltViewModel()
) {
    Column() {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp)
            ) {
                TitleText(text = "같이보면 좋을 상품", color = Color_Alife_Cyan, fontSize = 28.sp)
            }
            Column(modifier = Modifier.padding(20.dp)) {
                ProgresBarWithText(infoList = listOf("남은 예산"))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color_Alif_GrayBackground)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(modifier = Modifier.padding(20.dp, 10.dp)) {
                    Text(text = "칼로리 절감 강력 추천", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Card(
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            10.dp,
                            Alignment.CenterVertically
                        )
                    ) {
                        for (i in 0..3) {
                            FoodItem()
                        }
                    }
                }
                // 넣을수록 이득인 음식
                Column(modifier = Modifier.padding(20.dp, 10.dp)) {
                    Text(text = "계란 없는 식품들", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Card(
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            10.dp,
                            Alignment.CenterVertically
                        )
                    ) {
                        for (i in 0..4) {
                            FoodItem()
                        }
                    }
                }
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color_Alife_Cyan)
        ) {
            Text(
                text = "Total : 000kcal",
                modifier = Modifier.padding(20.dp, 10.dp),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun RegisterRecDietFoodScreenPreview() {
    RegisterRecDietFoodScreen()
}

