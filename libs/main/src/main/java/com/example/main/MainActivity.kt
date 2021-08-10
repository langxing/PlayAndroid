package com.example.main

import com.example.mine.databinding.ActivityMainBinding
import com.zxf.basic.base.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>() {

    override val mBinding: ActivityMainBinding
        get() = ActivityMainBinding.inflate(layoutInflater)

}