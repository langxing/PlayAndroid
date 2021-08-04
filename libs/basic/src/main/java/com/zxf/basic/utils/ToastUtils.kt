package com.zxf.basic.utils

import android.app.Application
import android.widget.Toast
import com.zxf.basic.BuildConfig

class ToastUtils {

    companion object {
        private var application: Application? = null

        fun init(app: Application) {
            application = app
        }

        fun show(msg: String) {
            Toast.makeText(application, msg, Toast.LENGTH_SHORT).show()
        }

        fun debugShow(msg: String) {
            if (BuildConfig.DEBUG) {
                Toast.makeText(application, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}