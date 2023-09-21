package com.wwwkr.baseproject_cleanarchitecture.view.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.wwwkr.baseproject_cleanarchitecture.R
import com.wwwkr.baseproject_cleanarchitecture.databinding.DialogLoadingBinding

class LoadingDialogFragment : DialogFragment() {

    private var userCancelledListener: (() -> Unit)? = null
    private var dismissListener: (() -> Unit)? = null

    private var alertDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = DialogLoadingBinding.inflate(
            inflater,
            container,
            false
        )

        view.tvLoading.text = arguments?.getString("text") ?: "로딩중입니다."
        return view.root
    }

    override fun onStart() {
        super.onStart()

        val isCancelable = arguments?.getBoolean("cancelable", true) ?: true

        dialog?.setCanceledOnTouchOutside(isCancelable)
        dialog?.setCancelable(isCancelable)
        dialog?.window?.let {
            it.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            it.setBackgroundDrawable(ColorDrawable())
            it.setDimAmount(0.5f)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        activity?.let {
            object : Dialog(it, theme) {
                override fun cancel() {
                    alertDialog = AlertDialog.Builder(it)
                        .setTitle(R.string.loading_cancel_title)
                        .setMessage(R.string.loading_cancel_message)
                        .setPositiveButton(R.string.positive) { dialog, which ->
                            userCancelledListener?.invoke()
                            super.cancel()
                        }
                        .setNegativeButton(R.string.negative) { dialog, which ->
                            dialog.dismiss()
                        }
                        .setCancelable(true)
                        .show()
                }
            }
        } ?: throw IllegalStateException("Activity cannot be null")


    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        userCancelledListener?.invoke()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.invoke()

        if (alertDialog?.isShowing == true) {
            alertDialog?.dismiss()
        }
    }

    fun setUserCancelledListener(listener: (() -> Unit)?): LoadingDialogFragment = apply {
        userCancelledListener = listener
    }

    fun setDismissListener(listener: (() -> Unit)?): LoadingDialogFragment = apply {
        dismissListener = listener
    }

    fun show(supportFragmentManager: FragmentManager): LoadingDialogFragment = apply {
        show(supportFragmentManager, TAG)
    }

    companion object {
        const val TAG = "LoadingDialogFragment"

        fun newInstance(
            text: String = "로딩중입니다.",
            cancelable: Boolean = true
        ): LoadingDialogFragment {
            val frag = LoadingDialogFragment()
            val args = Bundle()
            args.putString("text", text)
            args.putBoolean("cancelable", cancelable)
            frag.arguments = args
            return frag
        }

    }
}