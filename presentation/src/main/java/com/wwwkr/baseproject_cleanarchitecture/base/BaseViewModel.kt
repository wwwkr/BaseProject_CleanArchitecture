package com.wwwkr.baseproject_cleanarchitecture.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun showLoading(){
        viewModelScope.launch {
            _isLoading.value = true
        }
    }

    fun hideLoading(){
        viewModelScope.launch {
            _isLoading.value = false
        }
    }
}