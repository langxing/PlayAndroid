package com.example.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.common.service.ServiceManager
import com.example.mine.R
import com.example.mine.databinding.FragmentHomeBinding
import com.zxf.basic.base.BindingFragment
import com.zxf.basic.expand.signClick
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BindingFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun layout(): Int = R.layout.fragment_home

    override fun initView() {
        tvLogin.signClick {
            ServiceManager.startLogin(requireActivity() as AppCompatActivity) {

            }
        }
    }

    override fun initData() {

    }

    override val mViewModel: HomeViewModel
        get() = getViewModel()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

}