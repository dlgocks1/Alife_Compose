package com.alife.vegan.ui.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScopeInstance.weight
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
fun RegisterShoppingCart(
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
                TitleText(text = "식단 장바구니", color = Color_Alife_Cyan, fontSize = 28.sp)
            }
            Text(
                text = "선택 상품 N개",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp, 10.dp)
            )
            for (i in 0..5) {
                ShoppingCartItem()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp)
        ) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f)) {
                Text(text = "구매하기")
            }
        }
    }
}

@Composable
private fun ShoppingCartItem() {
    Column {
        Text(text = "상품 이름")
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                elevation = 2.dp
            ) {
                Image(
                    painterResource(R.drawable.img_dummy),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "5,000원 | 120g")
                        Text(text = "250kcal")
                    }
                    Checkbox(checked = true, onCheckedChange = {})
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "아침")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "점심")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "저녁")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RegisterShoppingCartPreview() {
    RegisterShoppingCart()
}

