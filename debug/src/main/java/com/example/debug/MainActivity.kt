package com.example.debug

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.common.Constant
import com.example.common.service.ServiceManager
import com.zxf.basic.expand.FindView
import com.zxf.basic.utils.MMKVUtils

class MainActivity : AppCompatActivity() {
    private val tvLogin: TextView by FindView(R.id.tvLogin)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isLogin = MMKVUtils.get().get<Boolean>(Constant.KEY_LOGIN)
        if (isLogin == true) {
            ServiceManager.startMain(this)
        } else {
            setContentView(R.layout.activity_debug)
            tvLogin.setOnClickListener {
                ServiceManager.startLogin(this) {

                }
                finish()
            }
        }
    }

}