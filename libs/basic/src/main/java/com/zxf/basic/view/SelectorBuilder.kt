package com.zxf.basic.view

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable

/**
 * Selector构建
 *
 * @author Jack 2021-05-01 17:57
 */
class SelectorBuilder {
    private var mSelectDrawable: Drawable? = null
    private var mUnSelectDrawable: Drawable? = null

    var selectDrawable: Drawable?
    get() = mSelectDrawable
    set(value) {
        mSelectDrawable = value
    }

    var unSelectDrawable: Drawable?
    get() = mUnSelectDrawable
    set(value) {
        mUnSelectDrawable = value
    }

    fun build(): Drawable {
        val drawable = StateListDrawable()
        mSelectDrawable?.apply {
            drawable.addState(arrayOf(android.R.attr.state_selected).toIntArray(), this)
        }
        mUnSelectDrawable?.apply {
            drawable.addState(arrayOf(-android.R.attr.state_selected).toIntArray(), this)
        }
        return drawable
    }
}