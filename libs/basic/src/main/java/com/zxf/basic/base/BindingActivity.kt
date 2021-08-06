package com.zxf.basic.base

import androidx.viewbinding.ViewBinding

abstract class BindingActivity<V : ViewBinding> : BaseActivity() {

    abstract val mBinding: V

    override fun layout(): Int = 0

    override fun initView() {
        setContentView(mBinding.root)
    }

}