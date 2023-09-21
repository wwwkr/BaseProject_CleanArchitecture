package com.wwwkr.baseproject_cleanarchitecture.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BaseProjectApp : Application(){

    companion object {
        lateinit var instance : BaseProjectApp
    }

    init {
        instance = this
    }

}