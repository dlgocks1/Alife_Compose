@file:OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)

package com.alife.vegan.ui.shpping

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alife.vegan.R
import com.skydoves.landscapist.glide.GlideImage
import java.text.DecimalFormat

@Composable
fun ShoppingResultScreen(
    navController: NavController = rememberNavController(),
    shoppingViewModel: ShoppingViewModel = hiltViewModel()
) {
    val searchText = shoppingViewModel.searchText.value
    val keyboardController = LocalSoftwareKeyboardController.current
    val decimalFormat = DecimalFormat("#,###")

    LaunchedEffect(key1 = Unit) {
        shoppingViewModel.getTodayFood("비건")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        SearchTextField(searchText, shoppingViewModel, keyboardController)
        if (shoppingViewModel.searchFoodList.value != null) {
            Text(
                text = "검색 결과 ${shoppingViewModel.searchFoodList.value!!.size}개",
                modifier = Modifier.padding(20.dp, 0.dp),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            if (shoppingViewModel.searchFoodList.value == null) {
                item {
                    Text(text = "검색 결과가 없습니다.", fontSize = 18.sp)
                }
            } else if (shoppingViewModel.searchFoodList.value!!.size > 0) {
                items(shoppingViewModel.searchFoodList.value!!.windowed(2, 2, true)) { item ->
                    Row(
                        modifier = Modifier.padding(20.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    navController.navigate("detail")
                                },
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            GlideImage(
                                imageModel = item[0].product_image,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f),
                                contentScale = ContentScale.Crop
                            )
                            Text(text = item[0].product_name, fontSize = 14.sp)
                            RankingIcon()
                            Text(
                                text = "${decimalFormat.format(item[0].price)}원",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    navController.navigate("detail")
                                },
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            if (item.size == 2) {
                                GlideImage(
                                    imageModel = item[1].product_image,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f),
                                    contentScale = ContentScale.Crop
                                )
                                Text(text = item[1].product_name, fontSize = 14.sp)
                                RankingIcon()
                                Text(
                                    text = "${decimalFormat.format(item[1].price)}원",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Column(modifier = Modifier.widthIn(max = 130.dp)) {
                        Text(text = "오늘의 제품 추천", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "금일의 건강식품으로\n비건(이)가 들어간 제품은 어떠신가요?",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    shoppingViewModel.todayFoodList.forEach {
                        GlideImage(
                            imageModel = it.product_image,
                            modifier = Modifier.size(180.dp, 190.dp),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }

}

@Composable
private fun RankingIcon() {
    Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_star_24),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color(0xF8FFC400)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_star_24),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color(0xF8FFC400)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_star_24),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color(0xF8FFC400)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_star_24),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color(0xF8FFC400)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_star_24),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color(0xF8FFC400)
        )
    }
}

@Composable
private fun SearchTextField(
    searchText: String,
    shoppingViewModel: ShoppingViewModel,
    keyboardController: SoftwareKeyboardController?
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 20.dp),
        value = searchText,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0x15000000),
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        onValueChange = {
            if (it.length <= 15) shoppingViewModel.handleTextChange(it)
        },
        shape = RoundedCornerShape(25.dp),
        singleLine = true,
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(onClick = {
                    shoppingViewModel.handleTextChange("")
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null
                    )
                }
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                shoppingViewModel.searchFood()
            })
    )
}

