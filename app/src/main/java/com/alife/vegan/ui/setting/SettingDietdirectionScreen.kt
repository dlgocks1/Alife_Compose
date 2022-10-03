package com.alife.vegan.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun SettingDietDirectionScreen(navController: NavController) {
  Column() {
    Button(onClick = { navController.popBackStack() }) {
      Text(text = "뒤로가기")
    }
    Text(text = "DietDirection Setting")
    Button(onClick = {
      navController.navigate("MainGraph") {
        popUpTo("MainGraph") {
          inclusive = true
        }
      }
    }) {
      Text(text = "완료")
    }
  }
}