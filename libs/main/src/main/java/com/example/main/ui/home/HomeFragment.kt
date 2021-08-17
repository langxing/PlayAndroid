package com.example.main.ui.home

import androidx.appcompat.app.AppCompatActivity
import com.example.common.service.ServiceManager
import com.example.main.R
import com.example.main.databinding.FragmentHomeBinding
import com.zxf.basic.base.BindingFragment
import com.zxf.basic.expand.signClick
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BindingFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun initView() {
        tvLogin.signClick {
            ServiceManager.startLogin(requireActivity() as AppCompatActivity) {

            }
        }
    }

    override fun initData() {

    }

}