package com.example.common.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar

/**
 * 统一标题
 *
 * @author Jack 2021-08-05 21:26
 */
class TitleBar : Toolbar {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {

    }

}