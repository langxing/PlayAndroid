package com.example.common.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.common.R
import com.example.common.extension.dip2px
import com.example.common.extension.sp2px
import kotlinx.android.synthetic.main.layout_titlebar.view.*

class TitleBar : Toolbar {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this)
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorTheme))
        setPadding(15f.sp2px(), 0, 15f.dip2px(), 0)
        val height = context.resources.getDimension(android.R.dimen.app_icon_size).toInt()
        layoutParams = MarginLayoutParams(MarginLayoutParams.MATCH_PARENT, height)
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.TitleBar)
        tvTitle.text = title
        title = ""
        tvTitle.setTextColor(typedArray.getColor(R.styleable.TitleBar_titleColor, Color.WHITE))
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
            typedArray.getDimension(R.styleable.TitleBar_titleSize, 18f.sp2px().toFloat()))
    }

}