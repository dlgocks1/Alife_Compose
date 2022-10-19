package com.alife.vegan.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alife.vegan.R

@Composable
fun BackArrowComponent(onClick: () -> Unit) {
    Column(modifier = Modifier.height(50.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_new_24),
            contentDescription = null,
            modifier = Modifier.clickable {
                onClick()
            }
        )
    }
}