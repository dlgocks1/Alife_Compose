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
fun RegisterDietBudgetScreen(
    navController: NavController = rememberNavController(),
    registerDietViewModel: RegisterDietViewModel = hiltViewModel()
) {
    val budget = registerDietViewModel.budget
    val decimalFormat = DecimalFormat("#,###")

    Column(modifier = Modifier.padding(20.dp)) {
        TitleText(text = "예산을 입력해 주세요.")
        TextField(
            value = "${decimalFormat.format(budget.value.toInt())}원",
            onValueChange = {
                budget.value = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "일일 예산을 입력해 주세요.") },
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                registerDietViewModel.budget.value =
                    (registerDietViewModel.budget.value.toInt() + 5000).toString()
            }) {
                Text(
                    text = "+5천", modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .weight(1f)
                )
            }
            Button(onClick = {
                registerDietViewModel.budget.value =
                    (registerDietViewModel.budget.value.toInt() + 10000).toString()
            }) {
                Text(
                    text = "+1만", modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .weight(1f)
                )
            }
            Button(onClick = {
                registerDietViewModel.budget.value =
                    (registerDietViewModel.budget.value.toInt() + 50000).toString()
            }) {
                Text(
                    text = "+5만", modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .weight(1f)
                )
            }
            Button(onClick = {
                registerDietViewModel.budget.value =
                    (registerDietViewModel.budget.value.toInt() + 100000).toString()
            }) {
                Text(
                    text = "+10만", modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .weight(1f)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        FillWidthButton {
            navController.navigate("register_diet_food")
        }
    }
}

