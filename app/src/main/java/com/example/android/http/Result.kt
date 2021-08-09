package com.example.android.http

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