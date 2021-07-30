package com.example.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.common.ServiceManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ServiceManager.startLogin(this)
        finish()
    }

}