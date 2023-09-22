package com.wwwkr.baseproject_cleanarchitecture.view.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.gson.Gson
import com.wwwkr.baseproject_cleanarchitecture.R
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

    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            binding.apply {
                lifecycleOwner = this@MainActivity
                viewModel = this@MainActivity.viewModel
            }.root
        )

        init()
    }
    private fun init(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.naviHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bnMenu.setupWithNavController(navController)
    }

}