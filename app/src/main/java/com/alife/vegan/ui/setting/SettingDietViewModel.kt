package com.alife.vegan.ui.setting

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SettingDietViewModel : ViewModel() {
    val gender = mutableStateOf("Male")
    val height = mutableStateOf("")
    val weight = mutableStateOf("")
    val exercise = mutableStateOf("Middle")
    val foodCategory = mutableStateOf(listOf<String>())
    val foodAllergy = mutableStateOf(listOf<String>())
    val vegunCategory = mutableStateOf(listOf<String>())
    val dietDirection = mutableStateOf(listOf<String>())

}