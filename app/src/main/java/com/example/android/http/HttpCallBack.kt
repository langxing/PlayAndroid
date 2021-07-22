package com.example.android.http

import com.example.android.utils.runOnUIThread
import com.zxf.basic.http.HttpCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class HttpCallBack<T> : Callback<BaseResponse<T>>  {

    override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
        runOnUIThread {
            if (response.isSuccessful) {
                val responseData = response.body()
                if (responseData?.errorCode == HttpCode.CODE_SUCCESS) {
                    onSuccess(responseData.data)
                } else {
                    onError(responseData?.errorCode ?: -1, responseData?.errorMsg ?: "数据获取失败")
                }
            } else {
                onError(-1, response.message())
            }
            onComplete()
        }
    }

    override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
        onError(-1, t.message ?: "数据获取失败")
        onComplete()
    }

    abstract fun onSuccess(data: T)

    abstract fun onError(code: Int, msg: String)

    open fun onComplete() {

    }
}