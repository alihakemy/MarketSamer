package com.hakemy.marketsamer.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.hakemy.marketsamer.R

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment(){

    private var _binding: VB? = null
    val binding get() = _binding!!

    private var dialog: AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startObserver()
        dialog = AlertDialog.Builder(requireContext()).create()
        val inflate = LayoutInflater.from(requireContext()).inflate(R.layout.progress, null)
        dialog?.setView(inflate)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog?.setCancelable(true)

        if (dialog?.window != null) {
            dialog?.window!!
                .setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        }

    }
    fun showProgress() {
        if (dialog != null && dialog?.isShowing == false) {
            dialog?.show()
        }
    }


    fun hideProgress() {
        if (dialog?.isShowing == true) {
            dialog?.cancel()
            dialog?.hide()
        }
    }
    open fun startObserver() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if (_binding == null) {
            _binding = inflate.invoke(inflater, container, false)
            onCreateBinding()
        }
        afterCreateView()
        handleClicks()

        return binding.root
    }

   abstract fun onCreateBinding()

    open fun afterCreateView() {}

    open fun handleClicks() {}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}