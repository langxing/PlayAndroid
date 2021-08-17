package com.example.common.service

import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import java.util.*

object ServiceManager {

    fun startLogin(activity: AppCompatActivity, callback: (result: ActivityResult) -> Unit) {
        val serviceLoader = ServiceLoader.load(ILoginService::class.java)
        serviceLoader.forEach {
            it.startLogin(activity, callback)
        }
    }

    fun startMine(activity: Activity) {
        val serviceLoader = ServiceLoader.load(IMineService::class.java)
        serviceLoader.forEach {
            it.startMine(activity)
        }
    }

    fun startMain(activity: AppCompatActivity) {
        val serviceLoader = ServiceLoader.load(IMainService::class.java)
        serviceLoader.forEach {
            it.startMain(activity)
        }
    }
}