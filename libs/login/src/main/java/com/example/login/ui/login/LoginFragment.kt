package com.example.login.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.common.Constant
import com.example.common.service.ServiceManager
import com.example.login.R
import com.example.login.data.Result
import com.example.login.databinding.FragmentLoginBinding
import com.example.login.ui.LoginViewModelFactory
import com.zxf.basic.base.BindingFragment
import com.zxf.basic.expand.signClick
import com.zxf.basic.expand.toast
import com.zxf.basic.utils.MMKVUtils
import com.zxf.basic.view.EditWatcher

class LoginFragment : BindingFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    private lateinit var loginResult: Observer<Result<String>>

    override fun initView() {
        mBinding.titlebar.onBackClick = {
            if (!findNavController().popBackStack()) {
                requireActivity().finish()
            }
        }
        val afterTextChangedListener = object : EditWatcher() {

            override fun afterTextChanged(s: Editable) {
                mViewModel.loginDataChanged(
                    mBinding.tvPersonUsername.text.toString(),
                    mBinding.tvPersonPassword.text.toString()
                )
            }

        }
        mBinding.tvPersonRegister.signClick {
            findNavController().navigate(R.id.registFragment)
        }
        mBinding.tvPersonUsername.addTextChangedListener(afterTextChangedListener)
        mBinding.tvPersonPassword.addTextChangedListener(afterTextChangedListener)
        mBinding.tvPersonPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                mViewModel.login(
                    mBinding.tvPersonUsername.text.toString(),
                    mBinding.tvPersonPassword.text.toString()
                )
            }
            false
        }
        mBinding.btnPersonLogin.setOnClickListener {
            mBinding.pbLoading.visibility = View.VISIBLE
            mViewModel.login(
                mBinding.tvPersonUsername.text.toString(),
                mBinding.tvPersonPassword.text.toString()
            ).observe(viewLifecycleOwner, loginResult)
        }
    }

    override fun initData() {
        mViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                mBinding.btnPersonLogin.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    mBinding.tvPersonUsername.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    mBinding.tvPersonPassword.error = getString(it)
                }
            })

        loginResult = Observer<Result<String>> { result ->
            when (result) {
                is Result.Success -> {
                    context?.apply {
                        MMKVUtils.get().put(Constant.KEY_LOGIN, true)
                        val intent = Intent()
                        val activity = requireActivity()
                        ServiceManager.startMain(activity)
                        intent.putExtra(Constant.KEY_DATA, "登录成功")
                        activity.setResult(Activity.RESULT_OK, intent)
                        activity.finish()
                    }
                }
                is Result.Error -> {
                    context?.apply {
                        toast(result.exception.message)
                    }
                }
            }
            mBinding.pbLoading.postDelayed({
                mBinding.pbLoading.visibility = View.GONE
            }, 500)
        }
    }

}