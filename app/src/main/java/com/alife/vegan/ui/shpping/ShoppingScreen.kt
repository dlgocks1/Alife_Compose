package com.alife.vegan.ui.shpping

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun ShoppingScreen() {
  LazyColumn(modifier = Modifier.fillMaxSize()) {
    item {
      Text("Shopping Tab")
    }
  }
}

@Preview
@Composable
fun PreviewShopping() {
  ShoppingScreen()
}