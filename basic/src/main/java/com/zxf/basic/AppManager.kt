package com.zxf.basic

import android.app.Application

class AppManager {
    private var app: Application? = null

    companion object {
        private val instance: AppManager by lazy {
            AppManager()
        }

        fun get() = instance
    }

    fun init(application: Application) {
        app = application
    }

    fun getApp(): Application {
        return app!!
    }
}