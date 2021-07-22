package com.example.android.http

sealed class Result {
    /**
     * 请求成功
     */
    data class Success<T>(val data: T)

    /**
     * 请求失败
     */
    data class Error(val code: Int, val msg: String)

    /**
     * 请求结束
     */
    class Complete
}