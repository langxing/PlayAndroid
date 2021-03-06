package com.example.common.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.BarUtils
import com.example.common.R
import com.example.common.extension.dp2px
import com.example.common.extension.sp2px
import kotlinx.android.synthetic.main.layout_titlebar.view.*

class TitleBar : Toolbar {
    lateinit var onBackClick: () -> Unit

    private var titleColor = Color.WHITE
    private var titleSize = 18f.sp2px().toFloat()
    private var mLeftIcon: Drawable? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this)
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorTheme))
        val barHeight = BarUtils.getStatusBarHeight()
        setPadding(15f.dp2px(), barHeight, 15f.dp2px(), barHeight/2)
        layoutParams = MarginLayoutParams(MarginLayoutParams.MATCH_PARENT, MarginLayoutParams.WRAP_CONTENT)
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.TitleBar)
        tvTitle.text = title
        title = ""
        mLeftIcon = typedArray.getDrawable(R.styleable.TitleBar_leftIcon)
        titleColor = typedArray.getColor(R.styleable.TitleBar_titleColor, Color.WHITE)
        titleSize = typedArray.getDimension(R.styleable.TitleBar_titleSize, 18f.sp2px().toFloat())
        if(mLeftIcon != null) {
            ivBack.setImageDrawable(mLeftIcon)
        }
        tvTitle.setTextColor(titleColor)
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize)
        typedArray.recycle()
        ivBack.setOnClickListener {
            if (this::onBackClick.isInitialized) {
                onBackClick.invoke()
            }
        }
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }

}