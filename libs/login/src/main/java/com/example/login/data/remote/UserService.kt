package com.example.login.data.remote

import com.example.login.data.http.BaseResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author Jack 2021-08-08 16:37
 */
interface UserService {

    /**
     * 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") userName: String,
              @Field("password") password: String): Call<BaseResponse<Any>>

    /**
     * 注册
     */
    @POST("user/register")
    @FormUrlEncoded
    fun regist(@Field ("username") userName: String,
               @Field ("password") password: String,
               @Field ("repassword") rePassword: String): Call<BaseResponse<Any>>
}