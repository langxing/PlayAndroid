package com.example.main

import android.app.Activity
import com.example.common.service.IMainService
import com.google.auto.service.AutoService
import com.zxf.basic.expand.start

@AutoService(IMainService::class)
class MainServiceImpl : IMainService {

    override fun startMain(activity: Activity) {
        activity.start<MainActivity>()
    }

}