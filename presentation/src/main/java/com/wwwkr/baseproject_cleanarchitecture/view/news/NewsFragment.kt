package com.wwwkr.baseproject_cleanarchitecture.view.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import com.wwwkr.baseproject_cleanarchitecture.components.UiState
import com.wwwkr.baseproject_cleanarchitecture.databinding.FragmentNewsBinding
import com.wwwkr.baseproject_cleanarchitecture.extensions.repeatOnStarted
import com.wwwkr.baseproject_cleanarchitecture.view.main.MainViewModel

/**
 * A fragment representing a list of Items.
 */
class NewsFragment : Fragment() {

    private val binding by lazy { FragmentNewsBinding.inflate(layoutInflater) }
    private val adapter by lazy { NewsRecyclerViewAdapter() }
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.apply {
        adapter = this@NewsFragment.adapter
        lifecycleOwner = this@NewsFragment
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                            adapter.submitList(response.articles)
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