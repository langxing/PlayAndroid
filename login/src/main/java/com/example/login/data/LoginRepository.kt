package com.example.login.data

import kotlinx.coroutines.flow.flow
import java.lang.Exception

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository {

    fun login(username: String?, password: String?) = flow {
        if (username.isNullOrEmpty()) {
            emit(Result.Error(Exception("用户名不能为空")))
        } else if (password.isNullOrEmpty()) {
            emit(Result.Error(Exception("密码不能为空")))
        } else if (username == "jack" && password == "123456") {
            emit(Result.Success("登录成功"))
        } else {
            emit(Result.Error(Exception("登录失败")))
        }
    }

}