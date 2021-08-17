package com.example.common.extension

import android.app.ActivityOptions
import android.content.Intent
import android.transition.Slide
import androidx.appcompat.app.AppCompatActivity

/**
 * 启动activity并加入转场动画
 */
inline fun <reified T> AppCompatActivity.start() {
    startActivity(
        Intent(this, T::class.java),
        ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
    )
}

fun AppCompatActivity.slide() {
    val trans = Slide().setDuration(500)
    window.enterTransition = trans
    window.exitTransition = trans
}

