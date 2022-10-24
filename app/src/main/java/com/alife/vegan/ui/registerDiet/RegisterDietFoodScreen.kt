package com.alife.vegan.ui.onboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alife.vegan.ui.components.FillWidthButton
import com.alife.vegan.ui.components.TitleText
import com.alife.vegan.ui.registerDiet.RegisterDietViewModel
import java.text.DecimalFormat

@Composable
fun RegisterDietFoodScreen(
    navController: NavController = rememberNavController(),
    registerDietViewModel: RegisterDietViewModel = hiltViewModel()
) {

    Column(modifier = Modifier.padding(20.dp)) {

    }
}

