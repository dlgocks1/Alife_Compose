package com.alife.vegan.ui.onboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alife.vegan.ui.components.CustomTextField
import com.alife.vegan.ui.components.FillWidthButton
import com.alife.vegan.ui.components.TitleText
import com.alife.vegan.ui.registerDiet.RegisterDietViewModel
import java.text.DecimalFormat

@Composable
fun RegisterDietBudgetScreen(
    navController: NavController = rememberNavController(),
    registerDietViewModel: RegisterDietViewModel = hiltViewModel()
) {
    val decimalFormat = DecimalFormat("#,###")
    val budget = registerDietViewModel.budget.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TitleText(text = "예산을 입력해 주세요.")
        Spacer(modifier = Modifier.height(20.dp))
        BasicTextField(
            value = budget.value,
            onValueChange = { registerDietViewModel.budget.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp)
                .height(40.dp)
                .background(
                    color = Color.White,
                )
                .border(BorderStroke(1.dp, Color.Black)),
        )
//        Row(modifier = Modifier.fillMaxWidth()) {
//            Button(onClick = {
////                registerDietViewModel.budget.value =
////                    (registerDietViewModel.budget.value.toInt() + 5000).toString()
//            }) {
//                Text(
//                    text = "+5천", modifier = Modifier
//                        .padding(10.dp, 0.dp)
//                        .weight(1f)
//                )
//            }
//            Button(onClick = {
////                registerDietViewModel.budget.value =
////                    (registerDietViewModel.budget.value.toInt() + 10000).toString()
//            }) {
//                Text(
//                    text = "+1만", modifier = Modifier
//                        .padding(10.dp, 0.dp)
//                        .weight(1f)
//                )
//            }
//            Button(onClick = {
////                registerDietViewModel.budget.value =
////                    (registerDietViewModel.budget.value.toInt() + 50000).toString()
//            }) {
//                Text(
//                    text = "+5만", modifier = Modifier
//                        .padding(10.dp, 0.dp)
//                        .weight(1f)
//                )
//            }
//            Button(onClick = {
////                registerDietViewModel.budget.value =
////                    (registerDietViewModel.budget.value.toInt() + 100000).toString()
//            }) {
//                Text(
//                    text = "+10만", modifier = Modifier
//                        .padding(10.dp, 0.dp)
//                        .weight(1f)
//                )
//            }
//        }
        Spacer(modifier = Modifier.weight(1f))
        FillWidthButton {
            navController.navigate("register_diet_food")
        }
    }
}

@Preview
@Composable
fun RegisetDietBudgetPreview() {
    RegisterDietBudgetScreen()
}

