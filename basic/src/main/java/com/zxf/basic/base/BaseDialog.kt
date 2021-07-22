package com.zxf.basic.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.LayoutRes

/**
 * 自定义dialog，解决dialog设置的背景和宽、高不生效的问题
 */
abstract class BaseDialog(context: Context) : Dialog(context) {

    @LayoutRes
    abstract fun layout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        initView()
        initData()
    }

    override fun show() {
        super.show()
        window?.apply {
            val rootView = decorView.rootView
            rootView.post {
                val layoutParams = attributes
                layoutParams?.width = rootView?.width
                layoutParams?.height = rootView?.height
                attributes = layoutParams
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                rootView.background = rootView.background
            }
        }
    }

    abstract fun initView()

    abstract fun initData()
}