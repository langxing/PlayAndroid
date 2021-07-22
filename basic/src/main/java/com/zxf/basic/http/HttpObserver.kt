package com.zxf.basic.http

import com.zxf.basic.base.BaseViewModel
import com.zxf.basic.utils.LogUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @param model 绑定viewmodel，在生命周期停止时，自动取消订阅
 */
abstract class HttpObserver<T>(private val model: BaseViewModel?) : Observer<HttpResponse<T>> {

    override fun onSubscribe(d: Disposable) {
        model?.getObservers()?.add(d)
        onStart()
    }

    override fun onNext(value: HttpResponse<T>) {
        LogUtils.d(log = "resp=${value?.toString()}")
        if (value.code == HttpCode.CODE_SUCCESS) {
            onSuccess(value.data!!)
        } else {
            onError(value.code, value.msg)
        }
    }

    override fun onError(e: Throwable) {
        onError(code = -1, msg = e.message ?: "network anomaly")
    }

    // 请求成功的回调
    abstract fun onSuccess(data: T)

    // 请求失败的回调
    open fun onError(code: Int, msg: String?) {

    }

    // 请求发起之前
    open fun onStart() {

    }

    // 请求结束后
    override fun onComplete() {

    }

}