package com.example.android.http

import com.example.login.data.http.Result

sealed class Result<T> {
    /**
     * 请求成功
     */
    data class Success<T>(val data: T): Result<T>()

    /**
     * 请求失败
     */
    data class Error(val code: Int, val msg: String): Result<Nothing>()

    /**
     * 请求结束
     */
    class Complete<Nothing> : Result<Nothing>()
}