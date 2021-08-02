package com.example.mine

import androidx.appcompat.app.AppCompatActivity
import com.example.common.service.ServiceManager
import com.zxf.basic.base.BaseFragment
import com.zxf.basic.expand.signClick
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment() {

    override fun layout(): Int = R.layout.fragment_mine

    override fun initView() {
        tvLogin.signClick {
            ServiceManager.startLogin(requireActivity() as AppCompatActivity) {

            }
        }
    }

    override fun initData() {

    }

}