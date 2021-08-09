package com.example.login.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.asLiveData
import com.example.login.data.LoginRepository
import com.example.login.data.Result

import com.example.login.R
import com.example.login.data.remote.UserService
import com.zxf.basic.base.BaseViewModel
import com.zxf.basic.http.RetrofitHelper

class LoginViewModel : BaseViewModel() {
    private val service = RetrofitHelper.get()
        .getRetrofit("https://www.wanandroid.com/")
        .create(UserService::class.java)

    private val loginRepository = LoginRepository(service)
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun login(username: String, password: String): LiveData<Result<String>> {
        val result = loginRepository.login(username, password)
        return result.asLiveData()
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}