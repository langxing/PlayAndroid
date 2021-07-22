package com.example.android.utils

import android.os.Handler
import android.os.Looper

fun runOnUIThread(block: () -> Unit) {
    Handler(Looper.getMainLooper()).post {
        block.invoke()
    }
}