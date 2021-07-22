package com.zxf.basic.http

import com.blankj.utilcode.util.NetworkUtils
import com.zxf.basic.base.BaseViewModel
import com.zxf.basic.utils.LogUtils
import com.zxf.basic.utils.ToastUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

fun okHttpClient(okhttpBuilder: OkHttpClient.Builder.() -> Unit): OkHttpClient {
    return OkHttpClient.Builder().also(okhttpBuilder).build()
}

fun retrofit(retrofitBuilder: Retrofit.Builder.() -> Unit): Retrofit {
    return Retrofit.Builder().also(retrofitBuilder).build()
}

/**
 * 简化安卓中的线程切换
 */
fun <T> Observable<T>.switchThread(): Observable<T> {
    return observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}

/**
 * 统一处理协程的请求异常
 */
suspend fun <T> Call<HttpResponse<T>>.handledResponse(
    viewmodel: BaseViewModel? = null,
    onStart: (() -> Unit)? = null,
    onComplete: (() -> Unit)? = null,
    onError: ((code: Int, msg: String?) -> Unit)? = null): T? {
    return try {
        val isAvailable = withContext(Dispatchers.IO) {
            !NetworkUtils.isConnected() || !NetworkUtils.isAvailable()
        }
        // 如果网络不可用直接返回null
        if (isAvailable) {
            ToastUtils.show("The network is unavailable, please check the network")
            return null
        }
        onStart?.invoke()
        viewmodel?.getCalls()?.add(this)
        val response: Response<HttpResponse<T>> = withContext(Dispatchers.IO) {
            execute()
        }
        onComplete?.invoke()
        if (response.isSuccessful) {
            val result = response.body()
            if (result?.code == HttpCode.CODE_SUCCESS) {
                result.data
            } else {
                onError?.invoke(result?.code ?: -1, result?.msg ?: "data loading failed")
                handledError(result?.code ?: -1, result?.msg ?: "data loading failed")
                null
            }
        } else {
            onError?.invoke( -1, "data loading failed")
            handledError( -1, "data loading failed")
            null
        }
    } catch (e: Exception) {
        onComplete?.invoke()
        handledError(-1, e.message)
        onError?.invoke( -1, "data loading failed")
        null
    }
}

fun handledError(code: Int, msg: String?) {
    LogUtils.d(log = "code=$code;msg=$msg")
}
