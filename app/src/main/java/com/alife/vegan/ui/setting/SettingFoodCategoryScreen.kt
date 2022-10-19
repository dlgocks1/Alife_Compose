@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.alife.vegan.ui.setting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alife.vegan.ui.components.BackArrowComponent
import com.alife.vegan.ui.components.FillWidthButton
import com.alife.vegan.ui.components.TitleText
import com.alife.vegan.ui.theme.Color_Alif_Gray
import com.alife.vegan.ui.theme.Color_Alife_Cyan

@Composable
fun SettingFoodCategoryScreen(
    navController: NavController,
    settingDietViewModel: SettingDietViewModel = hiltViewModel()
) {
    val foodCategory = listOf<String>("한식", "중식", "일식", "양식", "분식")
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BackArrowComponent {
            navController.popBackStack()
        }
        TitleText(text = "좋아하는 음식 카테고리를 선택하세요")
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(foodCategory) {
                FoodCategoryButton(it, settingDietViewModel)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        FillWidthButton {
            navController.navigate("setting_allergy")
        }
    }
}

@Composable
private fun FoodCategoryButton(text: String, settingDietViewModel: SettingDietViewModel) {
    val isSelected = settingDietViewModel.foodCategory.value.contains(text)
    OutlinedButton(
        onClick = {
            if (isSelected) {
                settingDietViewModel.foodCategory.value =
                    settingDietViewModel.foodCategory.value.minus(text)
            } else {
                settingDietViewModel.foodCategory.value =
                    settingDietViewModel.foodCategory.value.plus(text)
            }
        },
        modifier = Modifier.size(100.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(Color.White),
        border = BorderStroke(1.dp, if (isSelected) Color_Alife_Cyan else Color(0xffd1d5da))
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            color = if (isSelected) Color_Alife_Cyan else Color_Alif_Gray,
        )
    }
}