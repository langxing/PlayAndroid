package com.example.common

import android.app.Application
import com.example.common.data.local.AppDatabase
import com.zxf.basic.AppManager
import com.zxf.basic.utils.MMKVUtils

object GlobalContext {

    var application: Application? = null
        private set

    var database: AppDatabase? = null
        private set

    fun init(app: Application) {
        application = app
        MMKVUtils.get().init(app)
        AppManager.get().init(app)
        database = AppDatabase.getDataBase(app)
    }
}