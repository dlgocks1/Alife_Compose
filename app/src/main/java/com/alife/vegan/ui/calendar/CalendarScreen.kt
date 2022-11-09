package com.alife.vegan.ui.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alife.vegan.R
import com.alife.vegan.network.response.GetFoodByPriceResponse
import com.alife.vegan.network.response.GetFoodByPriceResponseItem
import com.alife.vegan.ui.calendar.Dummy.dayList
import com.alife.vegan.ui.components.CalendarItem
import com.alife.vegan.ui.theme.Color_Alife_Cyan
import com.skydoves.landscapist.glide.GlideImage
import kotlin.math.floor


object Dummy {
    val dayList = listOf(
        "Tue" to 11,
        "Wed" to 12,
        "Fri" to 13,
        "Sat" to 14,
        "Sun" to 15,
        "Sat" to 16,
        "Sun" to 17,
        "Mon" to 18
    )
}

@ExperimentalMaterialApi
@Composable
fun CalendarScreen(
    navController: NavController,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val clickedDate = remember {
        mutableStateOf(dayList.first())
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "주간 식단", fontSize = 27.sp, fontWeight = FontWeight.Bold)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        15.dp,
                        Alignment.CenterHorizontally
                    )
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                navController.navigate("RegisterDietGraph")
                            },
                        tint = Color(0xff707070),
                        contentDescription = "shopping_icon"
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_settings_24),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                navController.navigate("SettingGraph")
                            },
                        tint = Color(0xff707070),
                        contentDescription = "setting_icon",
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = "Month",
                    modifier = Modifier.padding(30.dp, 0.dp, 30.dp, 0.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp, 0.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    items(dayList) { item ->
                        CalendarItem(
                            date = item,
                            isClicked = clickedDate.value == item,
                        ) {
                            clickedDate.value = item
                        }
                    }
                }
            }
            DietListContainer(navController, viewModel.dietList.value)
        }
    }
}

@Composable
fun DietListContainer(navController: NavController, value: GetFoodByPriceResponse?) {
    val isEmpty = false
    if (value == null || value.isEmpty()) {
        EmptyDiet(navController)
    } else {
        DietList(value)
    }
}

@Composable
fun DietList(getFoodByPriceResponse: GetFoodByPriceResponse) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0x07000000))
            .padding(20.dp, 10.dp)
    ) {
        LazyColumn {
            item {
                DietListItem("아침", getFoodByPriceResponse.filter { it.wtime == "아침" })
            }
            item {
                DietListItem("점심", getFoodByPriceResponse.filter { it.wtime == "점심" })
            }
            item {
                DietListItem("저녁", getFoodByPriceResponse.filter { it.wtime == "저녁" })
            }

        }
    }
}

@Composable
fun DietListItem(item: String, itemList: List<GetFoodByPriceResponseItem>) {
    if (itemList.isEmpty()) return
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = item, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xff707070))
        Spacer(modifier = Modifier.height(5.dp))
        Card(
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.padding(20.dp, 20.dp)
            ) {
                itemList.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GlideImage(
                            imageModel = item.product_image,
                            contentDescription = "food_Img",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape),
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = item.product_name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff707070)
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = item.calory.toString(),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff707070)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0x08000000),
                    thickness = 1.dp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total ${floor(itemList.sumOf { it.calory })} Kcal",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xff707070)
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyDiet(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0x07000000))
            .padding(20.dp, 40.dp)
    ) {
        Card(
            shape = RoundedCornerShape(15),
            modifier = Modifier.background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 100.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "식단이 등록되지 않았어요!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { navController.navigate("RegisterDietGraph") },
                    shape = RoundedCornerShape(100.dp),
                    border = BorderStroke(1.dp, Color_Alife_Cyan),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    modifier = Modifier.wrapContentSize(),
                    contentPadding = PaddingValues(25.dp, 10.dp)
                ) {
                    Text(
                        text = "식단 선택하러 가기",
                        color = Color_Alife_Cyan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
    }
}
