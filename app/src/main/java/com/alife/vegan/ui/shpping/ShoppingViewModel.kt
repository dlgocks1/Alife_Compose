package com.alife.vegan.ui.shpping

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alife.vegan.domain.repository.FoodRepository
import com.alife.vegan.domain.repository.RegisterDietRepository
import com.alife.vegan.network.response.GetFoodByPriceResponse
import com.alife.vegan.network.response.GetFoodByPriceResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    private val registerDietRepository: RegisterDietRepository
) : ViewModel() {

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText
    var foodList = mutableStateListOf<GetFoodByPriceResponseItem>()
    var todayFoodList = mutableStateListOf<GetFoodByPriceResponseItem>()

    private val _saerchFoodList: MutableState<GetFoodByPriceResponse?> = mutableStateOf(null)
    val saerchFoodList: State<GetFoodByPriceResponse?> = _saerchFoodList

    fun handleTextChange(str: String) {
        _searchText.value = str
    }

    fun getTodayFood(searchStr: String) = viewModelScope.launch {
        foodRepository.searchFood(productName = searchStr)
            .collectLatest {
                it.let {
                    todayFoodList.addAll(it)
                }
            }
    }

    suspend fun getFoodByPrice(onComplete: () -> Unit = {}) {
        registerDietRepository.getFoodByPrice(60000,
            onStart = {

            },
            onComplete = {
                onComplete()
            }
        ).collectLatest {
            foodList.clear()
            it.forEach { item ->
                foodList.add(item)
            }
            foodList.shuffled().sortedBy { it -> it.product_name }.take(5)
        }
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