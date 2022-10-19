package com.alife.vegan.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alife.vegan.ui.components.BackArrowComponent
import com.alife.vegan.ui.components.FillWidthButton
import com.alife.vegan.ui.theme.Color_Alife_Cyan

@Composable
fun SettingGenderScreen(
    navController: NavController,
    settingDietViewModel: SettingDietViewModel = hiltViewModel()
) {
    
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BackArrowComponent(onClick = {
            navController.popBackStack()
        })
        GenderSelect(settingDietViewModel)
        BodySelect(settingDietViewModel)
        Spacer(modifier = Modifier.weight(1f))
        FillWidthButton(onClick = {
            navController.navigate("setting_exercise")
        })
    }
}


@Composable
private fun BodySelect(settingDietViewModel: SettingDietViewModel) {
    Column() {
        Text(text = "키와 체중을 입력하세요.", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            OutlinedTextField(
                value = settingDietViewModel.height.value,
                onValueChange = { settingDietViewModel.height.value = it },
                label = {
                    Text(text = "키(cm)")
                },
                modifier = Modifier.weight(1f),
            )
            OutlinedTextField(
                value = settingDietViewModel.weight.value,
                onValueChange = { settingDietViewModel.weight.value = it },
                label = {
                    Text(text = "체중(kg)")
                }, modifier = Modifier.weight(1f)
            )
        }
    }
}


@Composable
private fun GenderSelect(settingDietViewModel: SettingDietViewModel) {
    Column() {
        Text(text = "성별을 입력하세요", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .fillMaxWidth()
                .padding(0.dp, 20.dp)
                .border(
                    1.dp,
                    color = Color(
                        0xffd1d5da
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            TextButton(
                onClick = { settingDietViewModel.gender.value = "Male" },
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = if (settingDietViewModel.gender.value == "Male") Color_Alife_Cyan else Color.White
                    ),
                colors = ButtonDefaults.buttonColors(
                    if (settingDietViewModel.gender.value == "Male") Color_Alife_Cyan else Color.White
                )
            ) {
                Text(text = "남자")
            }

            Button(
                onClick = { settingDietViewModel.gender.value = "Female" },
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = if (settingDietViewModel.gender.value == "Female") Color_Alife_Cyan else Color.White
                    ),
                colors = ButtonDefaults.buttonColors(
                    if (settingDietViewModel.gender.value == "Female") Color_Alife_Cyan else Color.White
                )
            ) {
                Text(text = "여자")
            }

        }
    }
}
