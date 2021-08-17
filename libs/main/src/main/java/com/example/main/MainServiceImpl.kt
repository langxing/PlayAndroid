package com.example.main

import androidx.appcompat.app.AppCompatActivity
import com.example.common.extension.start
import com.example.common.service.IMainService
import com.google.auto.service.AutoService

@AutoService(IMainService::class)
class MainServiceImpl : IMainService {

    override fun startMain(activity: AppCompatActivity) {
        activity.start<MainActivity>()
    }

}
