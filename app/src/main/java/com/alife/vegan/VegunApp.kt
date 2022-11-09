package com.alife.vegan

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.alife.vegan.network.response.GetFoodByPriceResponseItem
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VegunApp : Application() {
}