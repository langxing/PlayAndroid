package com.example.common.extension

import com.example.common.GlobalContext

/**
 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
 */
fun Float.dip2px(): Int {
    val scale = GlobalContext.application!!.resources.displayMetrics.density;
    return (this * scale + 0.5f).toInt()
}

/**
 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
 */
fun Float.px2dip(): Int {
    val scale: Float = GlobalContext.application!!.resources.displayMetrics.density
    return (this / scale + 0.5f).toInt()
}

/**
 * 将px值转换为sp值
 */
fun Float.px2sp(): Int {
    val fontScale: Float = GlobalContext.application!!.resources.displayMetrics.scaledDensity
    return (this / fontScale + 0.5f).toInt()
}

/**
 * 将sp值转换为px值，保证文字大小不变
 */
fun Float.sp2px(): Int {
    val fontScale: Float = GlobalContext.application!!.resources.displayMetrics.scaledDensity
    return (this * fontScale + 0.5f).toInt()
}

