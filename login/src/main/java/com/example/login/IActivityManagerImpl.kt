package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.common.IActivityManager

class IActivityManagerImpl : IActivityManager {

    override fun start(activity: AppCompatActivity) {
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
    }

}