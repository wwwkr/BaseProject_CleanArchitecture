package com.wwwkr.baseproject_cleanarchitecture.view.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.gson.Gson
import com.wwwkr.baseproject_cleanarchitecture.base.BaseActivity
import com.wwwkr.baseproject_cleanarchitecture.components.ActivityEvent
import com.wwwkr.baseproject_cleanarchitecture.components.UiState
import com.wwwkr.baseproject_cleanarchitecture.databinding.ActivityMainBinding
import com.wwwkr.baseproject_cleanarchitecture.extensions.repeatOnStarted
import com.wwwkr.baseproject_cleanarchitecture.extensions.start
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            binding.apply {
                lifecycleOwner = this@MainActivity
                viewModel = this@MainActivity.viewModel
            }.root
        )


        viewModel.getNews("kr")
        subscribeUI()
    }


    private fun subscribeUI(){

        repeatOnStarted {
            viewModel.apply {
                getNewsStateFlow.collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            showLoading()
                        }


                        is UiState.Error -> {
                            Log.e("TAG","uiState.errorMessage ${uiState.errorMessage}")
                            hideLoading()
                        }

                        is UiState.Success -> {
                            val response = uiState.data
                            Log.e("TAG","getNews resp : ${Gson().toJson(response)}")
                            hideLoading()

                        }

                        UiState.Empty -> {}
                    }
                }
            }
        }
    }
}