package com.example.login.ui

import com.example.login.databinding.ActivityLoginBinding
import com.zxf.basic.base.BindingActivity

class LoginActivity : BindingActivity<ActivityLoginBinding>() {

    override val mBinding: ActivityLoginBinding
        get() = ActivityLoginBinding.inflate(layoutInflater)

}