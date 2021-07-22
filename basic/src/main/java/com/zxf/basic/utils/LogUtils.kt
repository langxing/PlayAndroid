package com.zxf.basic.utils

import android.util.Log

class LogUtils {

    companion object {
        private var isDebug = true

        private const val tag = "worship"

        fun init(isDebug: Boolean) {
            this.isDebug = isDebug
        }

        fun e(tag: String = this.tag, log: String) {
            if (isDebug) {
                Log.e(tag, log)
            }
        }

        fun d(tag: String = this.tag, log: String) {
            if (isDebug) {
                Log.e(tag, log)
            }
        }
    }
}