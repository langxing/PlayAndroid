package com.example.debug

import android.app.Application
import com.example.common.GlobalContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.init(this)
    }
}