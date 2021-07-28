package com.example.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.common.IActivityManager
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loader = ServiceLoader.load(IActivityManager::class.java)
        val iterator = loader.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            next.start(this)
        }
    }
}