package com.alife.vegan.ui.registerDiet

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alife.vegan.domain.repository.FoodRepository
import com.alife.vegan.domain.repository.RegisterDietRepository
import com.alife.vegan.network.request.RegisterDietRequest
import com.alife.vegan.network.request.RegisterDietRequestItem
import com.alife.vegan.network.response.GetFoodByPriceResponse
import com.alife.vegan.network.response.GetFoodByPriceResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterDietViewModel @Inject constructor(
    private val registerDietRepository: RegisterDietRepository,
    private val foodRepository: FoodRepository
) : ViewModel() {

    private val _budget = mutableStateOf("")
    val budget: State<String> = _budget

    private val _isExpand = mutableStateOf(false)
    val isExpand: State<Boolean> = _isExpand

    var listState = mutableStateOf(listOf("남은 예산"))
    var searchMode = mutableStateOf(false)
    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText
//    private val _saerchFoodList: MutableState<GetFoodByPriceResponse?> = mutableStateOf(null)
//    val saerchFoodList: State<GetFoodByPriceResponse?> = _saerchFoodList

    var foodList = mutableStateListOf<GetFoodByPriceResponseItem>()
    var searchSelectedFoodList = mutableStateListOf<GetFoodByPriceResponseItem>()
    var recFoodList = mutableStateListOf<GetFoodByPriceResponseItem>()

    fun getRecfoodList() {
        recFoodList.addAll(foodList.filter { !it.isSelected }.shuffled().take(8))
    }

    fun handleTextChange(str: String) {
        _searchText.value = str
    }

    fun searchFood() = viewModelScope.launch {
        foodRepository.searchFood(productName = _searchText.value)
            .collectLatest {
                searchSelectedFoodList.clear()
                it.forEach { item ->
                    searchSelectedFoodList.add(item)
                }
                searchSelectedFoodList.sortedBy { it -> it.product_name }
            }
    }

    suspend fun registerDiet(
        shoppingList: MutableList<GetFoodByPriceResponseItem>,
        onComplete: () -> Unit
    ) {
        val params = arrayListOf<RegisterDietRequestItem>()
        shoppingList
            .filter { it.isSelected }
            .forEach { item ->
                params.add(
                    RegisterDietRequestItem(
                        item.id,
                        wtime = when (item.time) {
                            1 -> "아침"
                            2 -> "점심"
                            else -> "저녁"
                        }
                    )
                )
            }

        Log.i("test", params.toString())
        registerDietRepository.registerDiet(
            params = params
        ).collectLatest {
            Log.i("test", it.toString())
            if (it.isNotEmpty()) {
                onComplete()
            }
        }
    }

    suspend fun getFoodByPrice(onComplete: () -> Unit) {
        registerDietRepository.getFoodByPrice(budget.value.toInt(),
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
            foodList.sortedBy { it -> it.product_name }
        }
    }

    fun handleIsExpand(isExpand: Boolean) {
        if (!isExpand) {
            listState = mutableStateOf(listOf("남은 예산", "탄수화물", "단백질", "지방", "당류"))
        } else {
            listState = mutableStateOf(listOf("남은 예산"))
        }
        _isExpand.value = !isExpand
    }

    fun addSearchFoods(item: GetFoodByPriceResponseItem) {
        searchSelectedFoodList.add(
            searchSelectedFoodList.indexOf(item),
            item.copy(isSelected = !item.isSelected)
        )
        searchSelectedFoodList.remove(item)
    }

    fun changeSelected(item: GetFoodByPriceResponseItem) {
        foodList.add(foodList.indexOf(item), item.copy(isSelected = !item.isSelected))
        foodList.remove(item)
    }

    fun changeRecSelected(item: GetFoodByPriceResponseItem) {
        recFoodList.add(recFoodList.indexOf(item), item.copy(isSelected = !item.isSelected))
        recFoodList.remove(item)
    }

    fun handleChangeBudget(budget: String) {
        _budget.value = budget
    }

    fun addBudget(budget: Int) {
        if (_budget.value.isEmpty()) {
            _budget.value = budget.toString()
            return
        }
        _budget.value = (_budget.value.toInt() + budget).toString()
    }

}