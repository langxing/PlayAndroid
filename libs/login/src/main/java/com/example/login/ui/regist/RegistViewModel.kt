package com.example.login.ui.regist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.data.LoginRepository
import com.example.login.data.Result
import com.example.login.data.remote.UserService
import com.zxf.basic.base.BaseViewModel
import com.zxf.basic.http.RetrofitHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * @author Jack 2021-08-07 21:49
 */
class RegistViewModel : BaseViewModel() {
    private val service = RetrofitHelper.get()
        .getRetrofit("https://www.wanandroid.com/")
        .create(UserService::class.java)
    private val repository = LoginRepository(service)

    @ExperimentalCoroutinesApi
    fun regist(username: String, password: String, rePassword: String, onStart: (() -> Unit)? = null, onCompletion: (() -> Unit)? = null):
            LiveData<Result<String>> {
        val liveData = MutableLiveData<Result<String>>()
        viewModelScope.launch {
            repository.regist(username, password, rePassword)
                .onStart {
                    onStart?.invoke()
                }
                .onCompletion {
                    onCompletion?.invoke()
                }.collect {
                    liveData.value = it
                }
        }
        return liveData
    }
}