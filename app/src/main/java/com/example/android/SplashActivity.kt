package com.example.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.common.Constant
import com.example.common.service.ServiceManager
import com.zxf.basic.expand.start
import com.zxf.basic.expand.toast
import com.zxf.basic.utils.MMKVUtils

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MMKVUtils.get().get<Boolean>(Constant.KEY_LOGIN)?.let {
            if (it) {
                start<MainActivity>()
            } else {
                ServiceManager.startLogin(this) { result ->
                    if (result.resultCode == RESULT_OK) {
                        val data = result.data?.getStringExtra(Constant.KEY_DATA)
                        toast(data)
                    }
                }
            }
            finish()
        }
    }
}