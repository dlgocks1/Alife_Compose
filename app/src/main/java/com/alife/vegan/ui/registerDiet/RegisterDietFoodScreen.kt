@file:OptIn(ExperimentalComposeUiApi::class)

package com.alife.vegan.ui.registerDiet

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alife.vegan.R
import com.alife.vegan.ui.components.BackArrowComponent
import com.alife.vegan.ui.components.FillWidthButton
import com.alife.vegan.ui.components.FoodItem
import com.alife.vegan.ui.components.TitleText
import com.alife.vegan.ui.theme.Color_Alif_Gray
import com.alife.vegan.ui.theme.Color_Alif_GrayBackground
import com.alife.vegan.ui.theme.Color_Alife_Cyan
import com.alife.vegan.ui.theme.Color_Alife_Green
import kotlinx.coroutines.launch


@Composable
fun RegisterDietFoodScreen(
    navController: NavController = rememberNavController(),
    registerDietViewModel: RegisterDietViewModel = hiltViewModel()
) {
    val searchText = registerDietViewModel.searchText.value
    val keyboardController = LocalSoftwareKeyboardController.current

    if (registerDietViewModel.searchMode.value) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_new_24),
                contentDescription = null,
                modifier = Modifier
                    .padding(20.dp, 20.dp, 20.dp, 0.dp)
                    .clickable {
                        registerDietViewModel.searchMode.value = false
                    }
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
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
                            if (it.length <= 15) registerDietViewModel.handleTextChange(it)
                        },
                        shape = RoundedCornerShape(25.dp),
                        singleLine = true,
                        trailingIcon = {
                            if (searchText.isNotEmpty()) {
                                IconButton(onClick = {
                                    registerDietViewModel.handleTextChange("")
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
                                registerDietViewModel.searchFood()
                            })
                    )
                }
                if (registerDietViewModel.searchSelectedFoodList.isEmpty()) {
                    item {
                        Text(text = "검색 결과가 없습니다.", fontSize = 18.sp)
                    }
                } else if (registerDietViewModel.searchSelectedFoodList.size > 0) {
                    item {
                        Column(
                            modifier = Modifier
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            registerDietViewModel.searchSelectedFoodList.map {
                                FoodItem(
                                    { item ->
                                        registerDietViewModel.addSearchFoods(item)
                                    }, it
                                )
                            }
                        }
                        FillWidthButton(text = "완료") {
                            registerDietViewModel.searchMode.value = false
                        }
                    }
                }

            }
        }
    } else {
        Column {
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
                    TitleText(text = "11월 30일", color = Color_Alife_Cyan, fontSize = 24.sp)
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

                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            keyboardController?.show()
                            registerDietViewModel.searchMode.value = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.buttonColors(Color_Alife_Cyan)
                    ) {
                        Text(text = "검색하여 추가하기", color = Color.White, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    // 검색 추가된 데이터
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
                            registerDietViewModel.searchSelectedFoodList.filter { it.isSelected }
                                .map {
                                    FoodItem(
                                        { item ->
                                            registerDietViewModel.changeSelected(item)
                                        }, it
                                    )
                                }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color_Alife_Cyan),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total : %.2f kcal".format(registerDietViewModel.foodList.filter { it.isSelected }
                        .sumOf { it.calory }),
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
}

@Composable
private fun DietProgresBarWithText(
    viewModel: RegisterDietViewModel,
    infoList: List<String>
) {
    val currentBudget = viewModel.foodList.filter { it.isSelected }.sumOf { it.price }
        .toFloat() + viewModel.searchSelectedFoodList.filter { it.isSelected }
        .sumOf { it.price }.toFloat()

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

