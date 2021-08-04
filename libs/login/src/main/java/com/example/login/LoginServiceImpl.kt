package com.example.login

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import com.example.common.service.ILoginService
import com.google.auto.service.AutoService

@AutoService(ILoginService::class)
class LoginServiceImpl : ILoginService {

    override fun startLogin(activity: AppCompatActivity, callback: (result: ActivityResult) -> Unit) {
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
    }

}