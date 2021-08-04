package com.zxf.basic.base

abstract class BaseLazyFragment : BaseFragment() {
    private var isLoad = false

    override fun onResume() {
        super.onResume()
        if (!isLoad && !isHidden) {
            isLoad = true
            lazyInit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoad = false
    }

    abstract fun lazyInit()

}