package com.example.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.login.data.LoginRepository
import com.example.login.data.remote.UserService
import com.example.login.ui.login.LoginViewModel
import com.example.login.ui.regist.RegistViewModel
import com.zxf.basic.http.RetrofitHelper

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java) || modelClass.isAssignableFrom(RegistViewModel::class.java)) {
            val service = RetrofitHelper.get().getRetrofit("https://www.wanandroid.com/").create(UserService::class.java)
            return LoginViewModel(
                loginRepository = LoginRepository(service)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}