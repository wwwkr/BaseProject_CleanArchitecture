package com.wwwkr.baseproject_cleanarchitecture.extensions

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.wwwkr.baseproject_cleanarchitecture.components.ActivityEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun LifecycleOwner.repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
    }
}

fun Activity.start(event: ActivityEvent<*>) {
    event.let {
        val intent = Intent(this, it.targetActivity)
        it.bundle?.let { bundle -> intent.putExtras(bundle) }
        it.flags?.let { flags -> flags.forEach { flag -> intent.addFlags(flag) } }
        startActivity(intent)
        if (it.isFinish) finish()
    }
}