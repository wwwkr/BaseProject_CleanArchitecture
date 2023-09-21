package com.wwwkr.baseproject_cleanarchitecture.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wwwkr.baseproject_cleanarchitecture.extensions.repeatOnStarted
import com.wwwkr.baseproject_cleanarchitecture.view.dialog.LoadingDialogFragment

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel : T
    private var loadingDialog: LoadingDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeUI()
    }

    private fun subscribeUI(){

        repeatOnStarted {
            viewModel.apply {
                isLoading.collect { isLoading ->
                    when(isLoading){
                        true -> showLoadingView()
                        false -> hideLoadingView()
                    }
                }
            }

        }
    }

    private fun showLoadingView(
        cancelledListener: (() -> Unit)? = null,
        dismissListener: (() -> Unit)? = null
    ) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialogFragment.newInstance()
                .setUserCancelledListener {
                    cancelledListener?.invoke()
                }
                .setDismissListener {
                    dismissListener?.invoke()
                    loadingDialog = null
                }
                .show(supportFragmentManager)
        }
    }

    private fun hideLoadingView() {
        loadingDialog?.dismissAllowingStateLoss()
        loadingDialog = null
    }
}