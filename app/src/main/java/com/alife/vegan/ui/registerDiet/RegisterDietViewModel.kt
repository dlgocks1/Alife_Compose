package com.alife.vegan.ui.registerDiet

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.alife.vegan.domain.repository.RegisterDietRepository
import com.alife.vegan.network.response.GetFoodByPriceResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class RegisterDietViewModel @Inject constructor(
    private val registerDietRepository: RegisterDietRepository
) : ViewModel() {

    private val _budget = mutableStateOf("")
    val budget: State<String> = _budget

    private val _isExpand = mutableStateOf(false)
    val isExpand: State<Boolean> = _isExpand

    var listState = mutableStateOf(listOf("남은 예산"))

    var foodList = mutableStateListOf<GetFoodByPriceResponseItem>()

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

    fun changeSelected(item: GetFoodByPriceResponseItem) {
        foodList.add(foodList.indexOf(item), item.copy(isSelected = !item.isSelected))
        foodList.remove(item)
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