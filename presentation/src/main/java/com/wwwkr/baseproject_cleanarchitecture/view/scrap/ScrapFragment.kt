package com.wwwkr.baseproject_cleanarchitecture.view.scrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.wwwkr.baseproject_cleanarchitecture.components.UiState
import com.wwwkr.baseproject_cleanarchitecture.databinding.FragmentScrapBinding
import com.wwwkr.baseproject_cleanarchitecture.extensions.repeatOnStarted
import com.wwwkr.baseproject_cleanarchitecture.view.main.MainViewModel

class ScrapFragment : Fragment() {

    private val binding by lazy { FragmentScrapBinding.inflate(layoutInflater) }
    private val adapter by lazy { ScrapRecyclerViewAdapter() }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.apply {
        adapter = this@ScrapFragment.adapter
        lifecycleOwner = this@ScrapFragment
        rvNews.itemAnimator = null
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        subscribeUI()
    }

    private fun init() {
        viewModel.getScrapNews()

        binding.swipeLayout.apply {
            setProgressViewEndTarget(false, 0)
            setOnRefreshListener {
                viewModel.getScrapNews()
                isRefreshing = false
            }
        }

        adapter.setOnScrapClickListener = { item ->

            if (item.isScraped) {
                viewModel.deleteNews(item = item, isScrapView = true)
            } else {
                viewModel.insertNews(item = item, isScrapView = true)
            }

        }

    }

    private fun subscribeUI() {

        repeatOnStarted {
            viewModel.apply {
                getScrapNewsStateFlow.collect { uiState ->
                    when (uiState) {

                        is UiState.Success -> {
                            val response = uiState.data
                            adapter.submitList(response)
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}