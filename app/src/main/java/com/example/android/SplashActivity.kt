package com.example.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zxf.basic.expand.start

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start<MainActivity>()
        finish()
    }
}