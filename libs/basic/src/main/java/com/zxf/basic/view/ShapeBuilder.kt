package com.zxf.basic.view

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable

/**
 * shape构建类
 *
 * @author Jack 2021-05-01 16:24
 */
class ShapeBuilder {
    //填充颜色
    private var mSolidColor = Color.TRANSPARENT
    //圆角大小
    private var mRadius = 0f
    //边框大小
    private var mStrokeWidth = 0
    //边框颜色
    private var mStrokeColor = Color.TRANSPARENT
    //渐变的类型
    private var mGradientType = GradientDrawable.Orientation.TOP_BOTTOM
    //渐变颜色从开始到结束
    private var mGradientColors = IntArray(0)

    var solid: Int
    get() = solid
    set(value) {
        mSolidColor = value
    }

    var radius: Float
    get() = radius
    set(value) {
        mRadius = value
    }

    var strokeWidth: Int
    get() = strokeWidth
    set(value) {
        mStrokeWidth = value
    }

    var strokeColor: Int
    get() = strokeColor
    set(value) {
        mStrokeColor = value
    }

    var gradientType: GradientDrawable.Orientation
    get() = gradientType
    set(value) {
        mGradientType = value
    }

    var gradientColors: IntArray
    get() = gradientColors
    set(value) {
        mGradientColors = value
    }

    fun build(): Drawable {
        val drawable = if (mGradientColors.size > 1) GradientDrawable(mGradientType, mGradientColors)
        else GradientDrawable()
        if (mGradientColors.size <= 1) {
            drawable.setColor(mSolidColor)
        }
        drawable.cornerRadius = mRadius
        drawable.setStroke(mStrokeWidth, mStrokeColor)
        return drawable
    }
}


