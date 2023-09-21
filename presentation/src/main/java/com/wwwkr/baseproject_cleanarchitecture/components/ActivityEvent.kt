package com.wwwkr.baseproject_cleanarchitecture.components

import android.app.Activity
import android.os.Bundle

data class ActivityEvent<T: Activity>(
    val targetActivity: Class<T>,
    val bundle: Bundle? = null,
    val isFinish: Boolean = false,
    val flags: List<Int>? = null
)