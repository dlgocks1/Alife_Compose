@file:OptIn(ExperimentalFoundationApi::class)

package com.alife.vegan.ui.setting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alife.vegan.R
import com.alife.vegan.ui.components.BackArrowComponent
import com.alife.vegan.ui.components.FillWidthButton
import com.alife.vegan.ui.components.TitleText
import com.alife.vegan.ui.theme.Color_Alif_Gray
import com.alife.vegan.ui.theme.Color_Alife_Cyan

@Composable
fun SettingVegunCategoryScreen(
    navController: NavController,
    settingDietViewModel: SettingDietViewModel = hiltViewModel()
) {
    val vegunCategory = listOf(
        "비건",
        "락토 베지테리언",
        "오보 베지테리언",
        "락토 오보 베지테리언",
        "페스코 베지테리언",
        "폴로 베지테리언",
        "플렉시 베지테리언",
        "상관없음",
    )
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BackArrowComponent {
            navController.popBackStack()
        }
        TitleText(text = stringResource(R.string.select_vegun_category))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(vegunCategory) {
                VegunCategoryBtn(it, settingDietViewModel)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        FillWidthButton {
            navigateToDietDirection(navController = navController)
        }
    }
}

@Composable
private fun VegunCategoryBtn(text: String, settingDietViewModel: SettingDietViewModel) {
    val isSelected = settingDietViewModel.vegunCategory.value.contains(text)
    OutlinedButton(
        onClick = {
            if (isSelected) {
                settingDietViewModel.vegunCategory.value =
                    settingDietViewModel.vegunCategory.value.minus(text)
            } else {
                settingDietViewModel.vegunCategory.value =
                    settingDietViewModel.vegunCategory.value.plus(text)
            }
        },
        modifier = Modifier.fillMaxWidth(1f),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        border = BorderStroke(1.dp, if (isSelected) Color_Alife_Cyan else Color.Gray)
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            color = if (isSelected) Color_Alife_Cyan else Color.Gray,
        )
    }
}


private fun navigateToDietDirection(navController: NavController) {
    navController.navigate("setting_diet_direction")
}

@Preview
@Composable
fun SettingVegunPreview() {
    SettingVegunCategoryScreen(navController = rememberNavController())
}