package com.zxf.basic.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView

abstract class BaseActivity : AppCompatActivity() {
    private var loadingProgress: LoadingPopupView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.transparentStatusBar(window)
        //避免切换横竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        if (layout() != 0) {
            setContentView(layout())
            initView()
            initData()
        }
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
    inline fun <reified M : ViewModel> getViewModel(): M = ViewModelProvider(this).get(M::class.java)
}