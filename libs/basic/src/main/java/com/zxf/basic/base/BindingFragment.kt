package com.zxf.basic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<V : ViewBinding, M : ViewModel> : BaseFragment() {

    abstract val mBinding: V

    abstract val mViewModel: M

    override fun layout(): Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }


}