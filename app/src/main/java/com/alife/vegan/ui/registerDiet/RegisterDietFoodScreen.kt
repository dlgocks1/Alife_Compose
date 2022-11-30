package com.alife.vegan.ui.registerDiet

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
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
import com.alife.vegan.ui.components.TitleText
import com.alife.vegan.ui.theme.Color_Alif_Gray
import com.alife.vegan.ui.theme.Color_Alif_GrayBackground
import com.alife.vegan.ui.theme.Color_Alife_Cyan
import com.alife.vegan.ui.theme.Color_Alife_Green


@Composable
fun RegisterDietFoodScreen(
    navController: NavController = rememberNavController(),
    registerDietViewModel: RegisterDietViewModel = hiltViewModel()
) {

//    LaunchedEffect(Unit) {
//        registerDietViewModel.getFoodByPrice()
//    }

    Column() {
        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp)
                .weight(1f)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp)
            ) {
                TitleText(text = "11월 14일", color = Color_Alife_Cyan, fontSize = 24.sp)
                TitleText(text = "식단을 선택해주세요.", fontSize = 24.sp)
            }

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DietProgresBarWithText(
                    registerDietViewModel,
                    infoList = registerDietViewModel.listState.value
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_new_24),
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(if (registerDietViewModel.isExpand.value) 270f else 90f)
                        .size(12.dp)
                        .clickable {
                            registerDietViewModel.handleIsExpand(registerDietViewModel.isExpand.value)
                        }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color_Alif_GrayBackground)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // 추천 리스트
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
                        registerDietViewModel.foodList.map {
                            FoodItem(
                                { item ->
                                    registerDietViewModel.changeSelected(item)
                                }, it
                            )
                        }
                    }
                }

                Text(text = "검색하여 음식 추가하기")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color_Alife_Cyan),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total : ${
                    String.format(
                        "@.2f",
                        registerDietViewModel.foodList.filter { it.isSelected }.sumOf { it.calory })
                }kcal",
                modifier = Modifier.padding(20.dp, 10.dp),
                color = Color.White
            )
            Text(
                text = "다음으로",
                modifier = Modifier
                    .padding(20.dp, 10.dp)
                    .clickable {
                        navController.navigate("register_rec_diet_food")
                    },
                color = Color.White
            )
        }
    }
}

@Composable
private fun DietProgresBarWithText(
    viewModel: RegisterDietViewModel,
    infoList: List<String>
) {
    var currentBudget = viewModel.foodList.filter { it.isSelected }.sumOf { it.price }.toFloat()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
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
                                .clip(RoundedCornerShape(20f))
                                .border(BorderStroke(1.dp, color = Color_Alife_Green))
                        ) {
                            drawRoundRect(
                                color = Color_Alife_Green,
                                size = Size(
                                    size.width * (currentBudget / viewModel.budget.value.toFloat()),
                                    size.height // 얼마큼 채워져있는지 %로 표현
                                ),
                                cornerRadius = CornerRadius(20f)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "$currentBudget / ${viewModel.budget.value}",
                    fontSize = 12.sp,
                    color = Color(0xFF474957)
                )
            }
        }
    }
}


@Preview
@Composable
fun RegisterDietFoodPreview() {
    RegisterDietFoodScreen()
}

