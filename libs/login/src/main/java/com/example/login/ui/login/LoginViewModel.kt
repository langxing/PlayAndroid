package com.example.login.ui.login

import android.util.Patterns
import androidx.lifecycle.*
import com.example.login.data.LoginRepository
import com.example.login.data.Result

import com.example.login.R
import com.example.login.data.remote.UserService
import com.zxf.basic.base.BaseViewModel
import com.zxf.basic.http.RetrofitHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    private val service = RetrofitHelper.get()
        .getRetrofit("https://www.wanandroid.com/")
        .create(UserService::class.java)

    private val loginRepository = LoginRepository(service)
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun login(username: String, password: String): LiveData<Result<String>> {
        val liveData = MutableLiveData<Result<String>>()
        viewModelScope.launch {
            loginRepository.login(username, password)
                .collect {
                    liveData.value = it
                }
        }
        return liveData
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