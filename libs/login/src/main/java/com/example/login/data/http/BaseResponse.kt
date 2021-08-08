package com.example.login.data.http

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(@SerializedName("data")
                        val data: T,
                        @SerializedName("errorCode")
                        val errorCode: Int = 0,
                        @SerializedName("errorMsg")
                        val errorMsg: String = "")