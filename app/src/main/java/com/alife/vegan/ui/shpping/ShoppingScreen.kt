@file:OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)

package com.alife.vegan.ui.shpping

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alife.vegan.R
import com.alife.vegan.ui.components.CustomTextField
import com.alife.vegan.ui.theme.Color_Alif_Gray
import com.alife.vegan.ui.theme.Color_Alif_GrayBackground

@Composable
fun ShoppingScreen(
    navController: NavController,
    shoppingViewModel: ShoppingViewModel = hiltViewModel()
) {

    val searchText = shoppingViewModel.searchText.value
    val list = listOf("제품1", "제품2", "제품3", "제품4")
    val keyboardController = LocalSoftwareKeyboardController.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = null,
                modifier = Modifier.size(100.dp, 30.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

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
                        navController.navigate("shopping_result")
                    })
            )
            Image(
                painter = painterResource(id = R.drawable.img_dummy_home),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "카테고리", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "더보기", fontSize = 14.sp, color = Color(0xFF1A94FF))
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                        contentDescription = null,
                        tint = Color(0xFF1A94FF),
                        modifier = Modifier
                            .size(16.dp)
                    )

                }
            }
            Text(
                text = "카테고리 준비중 입니다.", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(0.dp, 80.dp)
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .background(Color_Alif_GrayBackground)
            )

            Text(
                text = "당신을 위한 오늘의 제품",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp),
                textAlign = TextAlign.Start
            )
        }

        items(list.windowed(2, 2, true)) { item ->
            Row(
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_dummy),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = item[0], fontSize = 14.sp)
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
                    Text(text = "11,800원", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_dummy),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = item[1], fontSize = 14.sp)
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

                    Text(text = "11,800원", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewShopping() {
    ShoppingScreen(rememberNavController())
}