package com.alife.vegan.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun SettingExerciseScreen(navController: NavController) {
  Column() {
    Button(onClick = { navController.popBackStack() }) {
      Text(text = "뒤로가기")
    }
    Text(text = "Exercise Setting")
    Button(onClick = { navController.navigate("setting_diet_direction") }) {
      Text(text = "다음")
    }
  }
}