package com.zxf.basic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView

abstract class BaseFragment : Fragment() {
    private var loadingProgress: LoadingPopupView? = null

    abstract fun layout(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    abstract fun initView()

    abstract fun initData()

    protected fun showLoading() {
        if (loadingProgress == null) {
            loadingProgress = XPopup.Builder(requireActivity())
                .dismissOnBackPressed(true)
                .asLoading("加载中......")
        }
        loadingProgress?.show()
    }

    protected fun hideLoading() {
        loadingProgress?.dismiss()
    }

    /**
     * 获取viewmodel实例
     */
    inline fun <reified M : ViewModel> getViewModel(): M = ViewModelProvider(this).get(M::class.java)
}