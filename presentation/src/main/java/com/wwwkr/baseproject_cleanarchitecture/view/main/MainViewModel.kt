package com.wwwkr.baseproject_cleanarchitecture.view.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.wwwkr.baseproject_cleanarchitecture.base.BaseViewModel
import com.wwwkr.baseproject_cleanarchitecture.components.UiState
import com.wwwkr.domain.model.ArticleData
import com.wwwkr.domain.model.NewsRespData
import com.wwwkr.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : BaseViewModel() {

    private val _getNewsStateFlow = MutableStateFlow<UiState<NewsRespData>>(UiState.Empty)
    val getNewsStateFlow = _getNewsStateFlow.asStateFlow()

    private val _getScrapNewsStateFlow = MutableStateFlow<UiState<List<ArticleData>>>(UiState.Empty)
    val getScrapNewsStateFlow = _getScrapNewsStateFlow.asStateFlow()

    @OptIn(FlowPreview::class)
    fun getNews(country : String) = viewModelScope.launch {
        newsUseCase.getNews(country = country)
            .flatMapConcat { news ->
                newsUseCase.selectNews().map { savedNews ->
                    // news.articles 리스트와 savedNews 리스트의 content를 비교하여 isScraped를 true로 설정합니다.
                    news.articles?.forEach { article ->
                        savedNews.forEach { savedArticle ->
                            if (article.title == savedArticle.title) {
                                article.isScraped = true
                            }
                        }
                    }
                    // 변경된 news를 반환합니다.
                    news
                }
            }
            .onStart {
                _getNewsStateFlow.value = UiState.Loading
            }.catch {
                _getNewsStateFlow.value = UiState.Error(it.message.toString())
            }.collect {
                _getNewsStateFlow.value = UiState.Success(it)
            }
    }

    fun getScrapNews() = viewModelScope.launch {
        newsUseCase.selectNews()
            .collect {
                _getScrapNewsStateFlow.value = UiState.Success(it)
            }
    }
    fun insertNews(item: ArticleData, isScrapView : Boolean = false) = viewModelScope.launch {
        newsUseCase.insertNews(item = item.copy(isScraped = true))
            .collect {
                changeScraped(item = item, isScrapView = isScrapView, isInsert = true)
            }
    }

    fun deleteNews(item: ArticleData, isScrapView : Boolean = false) = viewModelScope.launch {
        newsUseCase.deleteNews(title = item.title.toString())
            .collect {
                changeScraped(item = item, isScrapView = isScrapView, isInsert = false)
            }
    }


    private fun changeScraped(item: ArticleData, isScrapView : Boolean = false, isInsert: Boolean){

        if(isScrapView) {
            val currentState = _getScrapNewsStateFlow.value
            if (currentState is UiState.Success) {
                val currentData = currentState.data
                val updatedArticles = currentData.filter { newsItem ->
                    newsItem.title != item.title
                }
                _getScrapNewsStateFlow.value = UiState.Success(updatedArticles)
            }
        }else {
            val currentState = _getNewsStateFlow.value
            if (currentState is UiState.Success) {
                val currentData = currentState.data
                val updatedArticles = currentData.articles?.map { newsItem ->
                    if (newsItem.title == item.title) {
                        // 변경된 아이템과 동일한 아이템을 찾아서 스크랩 상태를 변경합니다.
                        item.copy(isScraped = !item.isScraped)
                    } else {
                        newsItem
                    }
                }
                // 변경된 데이터로 UiState를 갱신합니다.
                _getNewsStateFlow.value = UiState.Success(currentData.copy(articles = updatedArticles))
            }
        }

    }


}