package com.example.android

import android.app.Application
import com.zxf.basic.AppManager
import com.zxf.basic.http.RetrofitHelper
import com.zxf.basic.utils.MMKVUtils
import com.zxf.basic.utils.ToastUtils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instace = this
        ToastUtils.init(this)
        AppManager.get().init(this)
        MMKVUtils.get().init(this)
        RetrofitHelper.get().setUrl("https://www.wanandroid.com/")
    }

    companion object {
        private lateinit var instace: Application

        fun get() = instace
    }
}