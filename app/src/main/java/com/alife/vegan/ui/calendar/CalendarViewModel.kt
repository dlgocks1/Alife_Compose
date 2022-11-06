package com.alife.vegan.ui.calendar

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alife.vegan.domain.repository.CalendarRepository
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: CalendarRepository
) : ViewModel() {
    private val _dietList = mutableStateOf(emptyList<Pair<String, String>>())
    val dietList: State<List<Pair<String, String>>> = _dietList

    fun getDietList() = viewModelScope.launch {
        repository.getDietList().collectLatest {
            _dietList.value = it
        }
    }

}