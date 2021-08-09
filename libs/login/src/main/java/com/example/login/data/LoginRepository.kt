package com.example.login.data

import com.example.login.data.http.HttpCallBack
import com.example.login.data.remote.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import java.lang.Exception

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(private val service: UserService) {

    fun login(username: String, password: String) = flow {
        when {
            username.isEmpty() -> {
                emit(Result.Error(Exception("用户名不能为空")))
            }
            password.isEmpty() -> {
                emit(Result.Error(Exception("密码不能为空")))
            }
            else -> {
                val response = service.login(username, password).execute()
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data?.errorCode == 0) {
                        emit(Result.Success("登录成功"))
                        return@flow
                    }
                }
                emit(Result.Error(Exception("登录失败")))
            }
        }
    }
        .flowOn(Dispatchers.IO)
        .catch {
            emit(Result.Error(Exception("登录失败")))
        }

    @ExperimentalCoroutinesApi
    fun regist(username: String, password: String, rePassword: String) = callbackFlow {
        var call: Call<*>? = null
        when {
            username.isEmpty() -> {
                sendBlocking(Result.Error(Exception("用户名不能为空")))
            }
            password.isEmpty() -> {
                sendBlocking(Result.Error(Exception("密码不能为空")))
            }
            rePassword.isEmpty() || rePassword != password -> {
                sendBlocking(Result.Error(Exception("确认密码必须和密码一致")))
            }
            else -> {
                call = service.login(username, password)
                call.enqueue(object: HttpCallBack<Any>() {
                    override fun onSuccess(data: Any) {
                        sendBlocking(Result.Success("注册成功"))
                    }

                    override fun onError(code: Int, msg: String) {
                        sendBlocking(Result.Error(Exception("注册失败")))
                    }

                })
            }
        }
        awaitClose {
            call?.cancel()
        }
    }
        .flowOn(Dispatchers.IO)
}