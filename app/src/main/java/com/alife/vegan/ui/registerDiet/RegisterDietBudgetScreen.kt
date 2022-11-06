package com.alife.vegan.ui.onboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import com.alife.vegan.ui.theme.Color_Alif_707070
import com.alife.vegan.ui.theme.Color_Alif_Gray
import com.alife.vegan.ui.theme.Color_Alife_C4C4C4
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat

@Composable
fun RegisterDietBudgetScreen(
    navController: NavController = rememberNavController(),
    registerDietViewModel: RegisterDietViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val decimalFormat = DecimalFormat("#,###")
    val budget = registerDietViewModel.budget
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        TitleText(text = "일일 예산을 입력해 주세요.", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = budget.value,
            onValueChange = {
                registerDietViewModel.handleChangeBudget(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                ),
            label = {
                Text(text = "일일 예산")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    registerDietViewModel.addBudget(
                        5000
                    )
                }, modifier = Modifier
                    .weight(1f),
                border = BorderStroke(1.dp, Color_Alif_Gray),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(
                    text = "+5천",
                )
            }
            Button(
                onClick = {
                    registerDietViewModel.addBudget(
                        10000
                    )
                }, modifier = Modifier.weight(1f),
                border = BorderStroke(1.dp, Color_Alif_Gray),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(
                    text = "+1만",
                )
            }
            Button(
                onClick = {
                    registerDietViewModel.addBudget(
                        50000
                    )
                }, modifier = Modifier.weight(1f),
                border = BorderStroke(1.dp, Color_Alif_Gray),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(
                    text = "+5만",
                )
            }
            Button(
                onClick = {
                    registerDietViewModel.addBudget(
                        100000
                    )
                }, modifier = Modifier.weight(1f),
                border = BorderStroke(1.dp, Color_Alif_Gray),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(
                    text = "+10만",
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        FillWidthButton {
            scope.launch {
                registerDietViewModel.getFoodByPrice {
                }
            }
            navController.navigate("register_diet_food")
        }
    }
}

@Preview
@Composable
fun RegisetDietBudgetPreview() {
    RegisterDietBudgetScreen()
}

