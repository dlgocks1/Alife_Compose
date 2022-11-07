package com.alife.vegan.ui.onboard

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alife.vegan.network.response.GetFoodByPriceResponseItem
import com.alife.vegan.ui.components.TitleText
import com.alife.vegan.ui.registerDiet.RegisterDietViewModel
import com.alife.vegan.ui.theme.Color_Alif_707070
import com.alife.vegan.ui.theme.Color_Alife_Cyan
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
import kotlin.math.floor

@Composable
fun RegisterShoppingCart(
    navController: NavController = rememberNavController(),
    registerDietViewModel: RegisterDietViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val shoppingList = remember {
        mutableStateListOf<GetFoodByPriceResponseItem>()
    }

    LaunchedEffect(key1 = Unit) {
        registerDietViewModel.foodList.filter { it.isSelected }.map {
            shoppingList.add(it)
        }
    }

    fun updateShoppingCart(index: Int, time: Int) {
        shoppingList[index] = shoppingList[index].copy(time = time)
    }

    fun updateIsSelected(index: Int, isSelected: Boolean) {
        shoppingList[index] = shoppingList[index].copy(isSelected = isSelected)
    }

    Column {
        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp)
            ) {
                TitleText(text = "식단 장바구니", color = Color_Alife_Cyan, fontSize = 28.sp)
            }
            Text(
                text = "선택 상품 ${shoppingList.count { it.isSelected }}개",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            shoppingList.map {
                ShoppingCartItem(
                    item = it,
                    updateTime = { time -> updateShoppingCart(shoppingList.indexOf(it), time) },
                    updateIsSelected = { selected ->
                        updateIsSelected(shoppingList.indexOf(it), !it.isSelected)
                    }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "상품 금액", fontSize = 16.sp, color = Color_Alif_707070)
                Text(
                    text = "${shoppingList.filter { it.isSelected }.sumOf { it.price }}원",
                    fontSize = 16.sp,
                    color = Color_Alif_707070
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "칼로리", fontSize = 16.sp, color = Color_Alif_707070)
                Text(
                    text = "${
                        floor(shoppingList.filter { it.isSelected }.sumOf { it.calory })
                    }kcal",
                    fontSize = 16.sp, color = Color_Alif_707070
                )
            }
            Button(
                onClick = {
                    scope.launch {
                        registerDietViewModel.registerDiet(shoppingList) {
                            navController.navigate("MainGraph") {
                                popUpTo("MainGraph")
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(Color_Alife_Cyan)
            ) {
                Text(text = "구매하기", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun ShoppingCartItem(
    item: GetFoodByPriceResponseItem,
    updateTime: (Int) -> Unit,
    updateIsSelected: (Boolean) -> Unit
) {
    val MORNING = 0
    val LUNCH = 1
    val DINNER = 2

    Column {
        Text(
            text = item.product_name,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(0xff707070)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp)
        ) {
            Card(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                elevation = 2.dp
            ) {
                GlideImage(
                    imageModel = item.product_image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${item.price}원 | ${item.amount}g",
                            color = Color_Alif_707070,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "${item.calory}kcal",
                            color = Color_Alif_707070,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Checkbox(
                        checked = item.isSelected,
                        onCheckedChange = { updateIsSelected(item.isSelected) })
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            updateTime(MORNING)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (item.time == MORNING) Color_Alife_Cyan else Color.Transparent
                        )
                    ) {
                        Text(
                            text = "아침",
                            color = if (item.time == MORNING) Color.White else Color.Gray
                        )
                    }
                    Button(
                        onClick = {
                            updateTime(LUNCH)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (item.time == LUNCH) Color_Alife_Cyan else Color.Transparent
                        )
                    ) {
                        Text(
                            text = "점심",
                            color = if (item.time == LUNCH) Color.White else Color.Gray
                        )
                    }
                    Button(
                        onClick = {
                            updateTime(DINNER)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (item.time == DINNER) Color_Alife_Cyan else Color.Transparent
                        )
                    ) {
                        Text(
                            text = "저녁",
                            color = if (item.time == DINNER) Color.White else Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}


@Preview
@Composable
fun RegisterShoppingCartPreview() {
    RegisterShoppingCart()
}

