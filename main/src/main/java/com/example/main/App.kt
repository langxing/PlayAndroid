package com.example.main

import android.app.Application
import com.zxf.basic.utils.MMKVUtils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MMKVUtils.get().init(this)
    }
}