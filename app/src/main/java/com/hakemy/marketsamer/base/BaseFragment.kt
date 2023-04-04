package com.hakemy.marketsamer.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment(){

    private var _binding: VB? = null
    val binding get() = _binding!!

    lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startObserver()
        progressDialog= ProgressDialog(requireContext())
        progressDialog.setMessage("loading")

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