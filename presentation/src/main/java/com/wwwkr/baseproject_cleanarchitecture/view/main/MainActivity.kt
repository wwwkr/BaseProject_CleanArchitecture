package com.wwwkr.baseproject_cleanarchitecture.view.main

import android.os.Bundle
import androidx.activity.viewModels
import com.wwwkr.baseproject_cleanarchitecture.base.BaseActivity
import com.wwwkr.baseproject_cleanarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

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


    }
}