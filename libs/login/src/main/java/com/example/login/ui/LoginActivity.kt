package com.example.login.ui

import com.example.common.extension.slide
import com.example.login.databinding.ActivityLoginBinding
import com.zxf.basic.base.BindingActivity

class LoginActivity : BindingActivity<ActivityLoginBinding>() {

    override fun initView() {
        super.initView()
        slide()
    }
}