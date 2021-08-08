package com.example.login.ui.regist

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.login.data.LoginRepository
import com.example.login.data.Result
import com.zxf.basic.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * @author Jack 2021-08-07 21:49
 */
class RegistViewModel(private val repository: LoginRepository) : BaseViewModel() {

    @ExperimentalCoroutinesApi
    fun regist(username: String, password: String, rePassword: String, onStart: (() -> Unit)? = null, onCompletion: (() -> Unit)? = null):
            LiveData<Result<String>> {
        return repository.regist(username, password, rePassword)
            .onStart {
                onStart?.invoke()
            }
            .onCompletion {
                onCompletion?.invoke()
            }
            .asLiveData()
    }
}