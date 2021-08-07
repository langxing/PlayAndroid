package com.example.common.extension

import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View

/**
 * 扩大view点击区域，默认30px
 */
fun View.expandTouchArea(size: Int = 30) {
    val parentView: View = parent as View
    parentView.post(Runnable {
        val rect = Rect()
        getHitRect(rect)
        rect.top -= size
        rect.bottom += size
        rect.left -= size
        rect.right += size
        parentView.touchDelegate = TouchDelegate(rect, this)
    })
}