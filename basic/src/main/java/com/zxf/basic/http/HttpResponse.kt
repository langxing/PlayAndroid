package com.zxf.basic.http

import com.google.gson.annotations.SerializedName

data class HttpResponse<T>(
    @SerializedName("data")
    val data: T,
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("msg")
    val msg: String = ""
)