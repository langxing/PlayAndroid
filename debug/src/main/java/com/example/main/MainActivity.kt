package com.example.main

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.common.service.ServiceManager
import com.zxf.basic.expand.FindView

class MainActivity : AppCompatActivity() {
    private val tvLogin: TextView by FindView(R.id.tvLogin)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvLogin.setOnClickListener {
            ServiceManager.startLogin(this) {

            }
            finish()
        }
    }

}