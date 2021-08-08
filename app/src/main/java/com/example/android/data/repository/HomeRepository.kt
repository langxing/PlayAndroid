package com.example.android.data.repository

import com.example.android.data.api.HomeService
import com.example.android.data.bean.ArticleData
import com.example.login.data.http.BaseResponse
import com.example.login.data.http.HttpCallBack
import com.example.login.data.http.Result
import com.example.login.data.http.Result.Error
import com.zxf.basic.http.HttpCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call

class HomeRepository(private val service: HomeService) {

    fun getArticleInfo(pageIndex: Int) = callbackFlow {
        val call = service.getArticleInfo(pageIndex)
        call.enqueue(object: HttpCallBack<ArticleData>() {
            override fun onSuccess(data: ArticleData) {
                offer(Result.Success(data))
            }

            override fun onError(code: Int, msg: String) {
                offer(Error(code, msg))
            }

            override fun onFailure(call: Call<BaseResponse<ArticleData>>, t: Throwable) {
                super.onFailure(call, t)
                close(t)
            }

            override fun onComplete() {
                super.onComplete()
                offer(Result.Complete())
            }
        })
        awaitClose {
            call.cancel()
        }
    }

    fun getBannerInfo() = flow {
        val response = service.getBanner().execute()
        if (response.isSuccessful) {
            val body = response.body()
            if (body?.errorCode == HttpCode.CODE_SUCCESS) {
                emit(Result.Success(body.data))
            } else {
                emit(Error(body?.errorCode ?: -1, body?.errorMsg ?: ""))
            }
        } else {
            emit(Error(-1, response.message()))
        }
        emit(Result.Complete())
    }.flowOn(Dispatchers.IO)
    .catch {
        emit(Error( -1, ""))
        emit(Result.Complete())
    }
}