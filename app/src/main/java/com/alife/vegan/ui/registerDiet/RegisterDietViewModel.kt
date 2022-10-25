package com.alife.vegan.ui.registerDiet

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterDietViewModel : ViewModel() {

    val budget =
        MutableStateFlow(
            TextFieldValue(
                text = "",
            )
        )
}