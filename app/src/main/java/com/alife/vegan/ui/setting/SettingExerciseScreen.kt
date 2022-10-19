package com.alife.vegan.ui.setting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alife.vegan.ui.components.BackArrowComponent
import com.alife.vegan.ui.components.FillWidthButton
import com.alife.vegan.ui.components.TitleText
import com.alife.vegan.ui.theme.Color_Alife_Cyan

@Composable
fun SettingExerciseScreen(
    navController: NavController,
    settingDietViewModel: SettingDietViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BackArrowComponent {
            navController.popBackStack()
        }
        TitleText(text = "평소 운동량을 선택해주세요.")
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            ExcersizeButton(
                "High", settingDietViewModel.exercise.value
            ) { settingDietViewModel.exercise.value = it }
            ExcersizeButton(
                "Middle", settingDietViewModel.exercise.value
            ) { settingDietViewModel.exercise.value = it }
            ExcersizeButton(
                "Low", settingDietViewModel.exercise.value
            ) { settingDietViewModel.exercise.value = it }
        }
        Spacer(modifier = Modifier.weight(1f))
        FillWidthButton {
            navController.navigate("setting_food_category")
        }
    }
}

@Composable
private fun ExcersizeButton(text: String, exercise: String, onClick: (String) -> Unit) {
    val isSelected = (exercise == text)
    Button(
        onClick = { onClick(text) },
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            1.dp,
            color = Color(
                0xffd1d5da
            ),
        ),
        colors = ButtonDefaults.buttonColors(if (isSelected) Color_Alife_Cyan else Color.White)
    ) {
        Text(text = text, color = if (isSelected) Color.White else Color.Black)
    }
}