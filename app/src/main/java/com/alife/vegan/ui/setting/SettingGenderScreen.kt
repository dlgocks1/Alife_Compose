package com.alife.vegan.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.alife.vegan.R
import com.alife.vegan.ui.Screen

@Composable
fun SettingGenderScreen(navController: NavController) {
  Column() {
    Button(onClick = { navController.popBackStack() }) {
      Text(text = "뒤로가기")
    }
    Text(text = "Gender Setting")
    Button(onClick = {
      navController.navigate("setting_exercise")

    }) {
      Text(text = "다음")
    }
  }
}
