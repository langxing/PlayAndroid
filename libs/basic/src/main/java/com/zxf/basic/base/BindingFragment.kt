package com.zxf.basic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<V : ViewBinding, M : ViewModel> : BaseFragment() {

    lateinit var mBinding: V
    private set

    abstract val mViewModel: M

    override fun layout(): Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = initBinding(inflater, container, savedInstanceState)
        return if (this::mBinding.isInitialized) mBinding.root else throw NullPointerException("必须先初始化binding")
    }

    abstract fun initBinding(inflater: LayoutInflater,
                             container: ViewGroup?,
                             savedInstanceState: Bundle?): V
}