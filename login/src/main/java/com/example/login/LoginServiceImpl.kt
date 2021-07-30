package com.example.login

import android.app.Activity
import android.content.Intent
import com.example.common.ILoginService
import com.google.auto.service.AutoService

@AutoService(ILoginService::class)
class LoginServiceImpl : ILoginService {

    override fun start(activity: Activity) {
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
    }

}