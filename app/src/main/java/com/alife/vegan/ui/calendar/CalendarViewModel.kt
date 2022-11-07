package com.alife.vegan.ui.calendar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alife.vegan.domain.repository.CalendarRepository
import com.alife.vegan.network.response.GetFoodByPriceResponse
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: CalendarRepository
) : ViewModel() {
    private val _dietList: MutableState<GetFoodByPriceResponse?> = mutableStateOf(null)
    val dietList: State<GetFoodByPriceResponse?> = _dietList

    init {
        getDietList()
    }

    private fun getDietList() = viewModelScope.launch {
        repository.getDietList().collectLatest {
            it?.let {
                _dietList.value = it
            }
        }
    }

}