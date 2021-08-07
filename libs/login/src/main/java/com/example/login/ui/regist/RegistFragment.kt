package com.example.login.ui.regist

import androidx.navigation.fragment.findNavController
import com.example.login.databinding.FragmentRegistBinding
import com.zxf.basic.base.BindingFragment
import com.zxf.basic.expand.signClick
import kotlinx.android.synthetic.main.fragment_regist.*

/**
 * 注册页
 *
 * @author Jack 2021-08-07 21:48
 */
class RegistFragment : BindingFragment<FragmentRegistBinding, RegistViewModel>() {

    override fun initView() {
        titlebar.signClick {
            findNavController().popBackStack()
        }
    }

    override fun initData() {

    }

    override val mBinding: FragmentRegistBinding
        get() = FragmentRegistBinding.inflate(layoutInflater)
    override val mViewModel: RegistViewModel
        get() = getViewModel()
}