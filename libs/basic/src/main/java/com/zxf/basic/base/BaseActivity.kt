package com.zxf.basic.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView

abstract class BaseActivity : AppCompatActivity() {
    private var loadingProgress: LoadingPopupView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        initView()
        initData()
    }

    abstract fun layout(): Int

    open fun initView() {}

    open fun initData() {}

    protected fun showLoading() {
        if (loadingProgress == null) {
            loadingProgress = XPopup.Builder(this)
                .dismissOnBackPressed(true)
                .asLoading("")
        }
        loadingProgress?.show()
    }

    protected fun hideLoading() {
        loadingProgress?.dismiss()
    }

    /**
     * 获取viewmodel实例
     */
    fun <M : ViewModel> get(clazz: Class<M>): M = ViewModelProvider(this).get(clazz)
}