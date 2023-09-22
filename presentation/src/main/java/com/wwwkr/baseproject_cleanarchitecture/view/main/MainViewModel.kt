package com.wwwkr.baseproject_cleanarchitecture.view.main

import androidx.lifecycle.viewModelScope
import com.wwwkr.baseproject_cleanarchitecture.base.BaseViewModel
import com.wwwkr.baseproject_cleanarchitecture.components.UiState
import com.wwwkr.domain.model.NewsRespData
import com.wwwkr.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val newsUseCase: NewsUseCase) : BaseViewModel() {

    private val _getNewsStateFlow = MutableStateFlow<UiState<NewsRespData>>(UiState.Empty)
    val getNewsStateFlow = _getNewsStateFlow.asStateFlow()

    fun getNews(country : String) = viewModelScope.launch {
        newsUseCase.getNews(country = country)
            .onStart {
                _getNewsStateFlow.value = UiState.Loading
            }.catch {
                _getNewsStateFlow.value = UiState.Error(it.message.toString())
            }.collect {
                _getNewsStateFlow.value = UiState.Success(it)
            }
    }
}