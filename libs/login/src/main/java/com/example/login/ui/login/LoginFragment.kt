package com.example.login.ui.login

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.common.Constant
import com.example.common.service.ServiceManager
import com.example.login.data.Result
import com.example.login.databinding.FragmentLoginBinding
import com.zxf.basic.base.BindingFragment
import com.zxf.basic.expand.toast
import com.zxf.basic.utils.MMKVUtils
import com.zxf.basic.view.EditWatcher
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BindingFragment<FragmentLoginBinding, LoginViewModel>() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginResult: Observer<Result<String>>

    override fun initView() {
        val afterTextChangedListener = object : EditWatcher() {

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    mBinding.tvPersonUsername.text.toString(),
                    mBinding.tvPersonPassword.text.toString()
                )
            }

        }
        mBinding.tvPersonUsername.addTextChangedListener(afterTextChangedListener)
        mBinding.tvPersonPassword.addTextChangedListener(afterTextChangedListener)
        mBinding.tvPersonPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    mBinding.tvPersonUsername.text.toString(),
                    mBinding.tvPersonPassword.text.toString()
                )
            }
            false
        }
        mBinding.btnPersonLogin.setOnClickListener {
            mBinding.pbLoading.visibility = View.VISIBLE
            loginViewModel.login(
                mBinding.tvPersonUsername.text.toString(),
                mBinding.tvPersonPassword.text.toString()
            ).observe(viewLifecycleOwner, loginResult)
        }
    }

    override fun initData() {
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
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

    override val mBinding: FragmentLoginBinding
        get() = FragmentLoginBinding.inflate(layoutInflater)
    override val mViewModel: LoginViewModel
        get() = getViewModel()

}