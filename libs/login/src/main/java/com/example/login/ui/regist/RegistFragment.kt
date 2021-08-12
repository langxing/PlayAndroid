package com.example.login.ui.regist

import android.text.Editable
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.data.Result
import com.example.login.databinding.FragmentRegistBinding
import com.zxf.basic.base.BindingFragment
import com.zxf.basic.expand.signClick
import com.zxf.basic.expand.toast
import com.zxf.basic.view.EditWatcher

/**
 * 注册页
 *
 * @author Jack 2021-08-07 21:48
 */
class RegistFragment : BindingFragment<FragmentRegistBinding, RegistViewModel>() {

    override fun initView() {
        mBinding.titlebar.onBackClick =  {
            findNavController().popBackStack()
        }
        val textWatcher = object: EditWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                mBinding.btnPersonRegister.isEnabled =  mBinding.tvPersonUsername.text.isNotEmpty()
                    && mBinding.tvPersonPassword.text!!.isNotEmpty()
                    && mBinding.tvPersonPasswordAgain.text!!.isNotEmpty()
            }

        }
        mBinding.tvPersonUsername.addTextChangedListener(textWatcher)
        mBinding.tvPersonPassword.addTextChangedListener(textWatcher)
        mBinding.tvPersonPasswordAgain.addTextChangedListener(textWatcher)
        mBinding.btnPersonRegister.signClick {
            mViewModel.regist(
                mBinding.tvPersonUsername.text.toString(),
                mBinding.tvPersonPassword.text.toString(),
                mBinding.tvPersonPasswordAgain.text.toString(),
            ).observe(viewLifecycleOwner, { result ->
                when (result) {
                    is Result.Success -> {
                        context?.toast("注册成功")
                        findNavController().popBackStack(R.id.loginFragment, false)
                    }
                    is Result.Error -> {
                        context?.toast("注册失败")
                    }
                }
            })
        }
    }

    override fun initData() {

    }

}