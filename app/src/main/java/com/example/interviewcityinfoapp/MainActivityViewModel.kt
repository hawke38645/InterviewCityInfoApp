package com.example.interviewcityinfoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel() : ViewModel() {

    private val cityInfoRepo: CityInfoRepository = CityInfoRepository()

    init {
        fetchCityInfoList()
    }

    private val _cityListState: MutableStateFlow<List<CityInfo>?> =
        MutableStateFlow(listOf())
    private val _isOptionsExpanded: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val cityListState = _cityListState.asStateFlow()
    val isOptionsExpanded = _isOptionsExpanded.asStateFlow()

    fun fetchCityInfoList() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    _cityListState.value = cityInfoRepo.getCityInfoList()
                }
            } catch (e: Exception) {
                if (e is CancellationException) ensureActive()
            }

        }

    }

    fun updateIsOptionsExpandedState(newIsOptionsExpandedState: Boolean) {
        _isOptionsExpanded.value = newIsOptionsExpandedState
    }

    fun sortCityInfoListAscending() {
        _cityListState.value = _cityListState.value?.sortedBy { it.name }
    }

    fun sortCityInfoListDescending() {
        _cityListState.value = _cityListState.value?.sortedByDescending { it.name }
    }
}