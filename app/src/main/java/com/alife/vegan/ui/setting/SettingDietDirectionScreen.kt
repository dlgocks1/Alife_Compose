package com.alife.vegan.ui.setting

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alife.vegan.R
import com.alife.vegan.ui.components.BackArrowComponent
import com.alife.vegan.ui.components.FillWidthButton
import com.alife.vegan.ui.components.TitleText
import com.alife.vegan.ui.theme.Color_Alif_Gray
import com.alife.vegan.ui.theme.Color_Alife_Cyan
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun SettingDietDirectionScreen(
    navController: NavController,
    settingDietViewModel: SettingDietViewModel = hiltViewModel()
) {
    val dietDirection = listOf(
        "고단백식단",
        "저탄수식단",
        "저당식단",
        "저염식",
        "저열당식단",
    )
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BackArrowComponent {
            navController.popBackStack()
        }
        TitleText(text = stringResource(R.string.select_vegun_category))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            crossAxisSpacing = 10.dp,
            mainAxisSpacing = 10.dp
        ) {
            dietDirection.map {
                DietDirectionBtn(it, settingDietViewModel)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        FillWidthButton {
            Log.i("SettingGraph", settingDietViewModel.foodAllergy.value.toString())
            navigateToMain(navController = navController)
        }
    }
}

private fun navigateToMain(navController: NavController) {
    navController.navigate("MainGraph") {
        popUpTo("SettingGraph") {
            inclusive = true
        }
    }
}

@Composable
private fun DietDirectionBtn(text: String, settingDietViewModel: SettingDietViewModel) {
    val isSelected = settingDietViewModel.dietDirection.value.contains(text)
    OutlinedButton(
        onClick = {
            if (isSelected) {
                settingDietViewModel.dietDirection.value =
                    settingDietViewModel.dietDirection.value.minus(text)
            } else {
                settingDietViewModel.dietDirection.value =
                    settingDietViewModel.dietDirection.value.plus(text)
            }
        },
        modifier = Modifier.padding(10.dp, 0.dp),
        shape = RoundedCornerShape(5.dp),
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