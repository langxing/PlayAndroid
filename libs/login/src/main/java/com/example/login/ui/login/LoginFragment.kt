package com.example.login.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.common.Constant
import com.example.common.service.ServiceManager
import com.example.login.R
import com.example.login.data.Result
import com.zxf.basic.expand.toast
import com.zxf.basic.utils.MMKVUtils
import com.zxf.basic.view.EditWatcher
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        val usernameEditText = username
        val passwordEditText = password
        val loginButton = login
        val loadingProgressBar = loading

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        val afterTextChangedListener = object : EditWatcher() {

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }

        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        val loginResult = Observer<Result<String>> { result ->
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
            loadingProgressBar.postDelayed({
                loadingProgressBar.visibility = View.GONE
            }, 500)
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            ).observe(viewLifecycleOwner, loginResult)
        }
    }

}