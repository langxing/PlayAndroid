package com.example.login

import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import com.example.common.extension.start
import com.example.common.service.ILoginService
import com.example.login.ui.LoginActivity
import com.google.auto.service.AutoService

@AutoService(ILoginService::class)
class LoginServiceImpl : ILoginService {

    override fun startLogin(activity: AppCompatActivity, callback: (result: ActivityResult) -> Unit) {
        activity.start<LoginActivity>()
    }

}