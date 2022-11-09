package com.alife.vegan.ui.shpping

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alife.vegan.domain.repository.FoodRepository
import com.alife.vegan.network.response.GetFoodByPriceResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _saerchFoodList: MutableState<GetFoodByPriceResponse?> = mutableStateOf(null)
    val saerchFoodList: State<GetFoodByPriceResponse?> = _saerchFoodList

    fun handleTextChange(str: String) {
        _searchText.value = str
    }

    fun searchFood() = viewModelScope.launch {
        foodRepository.searchFood(productName = _searchText.value)
            .collectLatest {
                it.let {
                    _saerchFoodList.value = it
                }
            }
    }
}