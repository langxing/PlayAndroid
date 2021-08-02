package com.example.android

import android.app.Activity
import android.content.Intent
import com.example.common.service.IMainService
import com.google.auto.service.AutoService

@AutoService(IMainService::class)
class MainServiceImpl : IMainService {

    override fun startMain(activity: Activity) {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }

}