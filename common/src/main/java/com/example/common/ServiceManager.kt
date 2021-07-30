package com.example.common

import android.app.Activity
import java.util.*

object ServiceManager {

    fun startLogin(activity: Activity) {
        val serviceLoader = ServiceLoader.load(ILoginService::class.java)
        serviceLoader.forEach {
            it.start(activity)
        }
    }

    fun startMine(activity: Activity) {
        val serviceLoader = ServiceLoader.load(IMineService::class.java)
        serviceLoader.forEach {
            it.startMine(activity)
        }
    }
}