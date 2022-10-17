package com.alife.vegan.ui.onboard

import android.widget.RadioGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alife.vegan.R
import com.alife.vegan.ui.theme.Color_Alife_Cyan
import com.alife.vegan.ui.theme.Color_Alife_Green

@Composable
fun OnboardUserInfo(navController: NavController = rememberNavController()) {

    val gender = remember {
        mutableStateOf("Male")
    }

    Scaffold(
        topBar = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_new_24),
                contentDescription = null
            )
        }
    ) {
        GenderSelect(gender)
//        BodySelect()
        Text(text = "키와 체중을 입력하세요.")
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(value = "", onValueChange = {}, label = {
                Text(text = "키")
            }, modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(value = "", onValueChange = {}, label = {
                Text(text = "체중")
            }, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun GenderSelect(gender: MutableState<String>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "성별을 입력하세요")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
        ) {
            Button(
                onClick = { gender.value = "Male" },
                modifier = Modifier
                    .background(color = if (gender.value == "Male") Color_Alife_Cyan else Color.White)
                    .border(
                        1.dp,
                        color = if (gender.value == "Male") Color_Alife_Cyan else Color(
                            0xffd1d5da
                        ),
                        shape = RoundedCornerShape(5.dp)
                    )
            ) {
                Text(text = "남자")
            }

            Button(
                onClick = { gender.value = "Female" },
                modifier = Modifier
                    .background(color = if (gender.value == "Female") Color_Alife_Cyan else Color.White)
                    .border(
                        1.dp,
                        color = if (gender.value == "Female") Color_Alife_Cyan else Color(
                            0xffd1d5da
                        ),
                        shape = RoundedCornerShape(5.dp)
                    )
            ) {
                Text(text = "여자")
            }

        }
    }
}