package com.example.common

import android.app.Application

object GlobalContext {

    var application: Application? = null
        private set

    fun init(app: Application) {
        application = app
    }
}