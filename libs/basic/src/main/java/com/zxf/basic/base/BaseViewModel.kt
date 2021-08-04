package com.zxf.basic.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zxf.basic.annotation.LoadingState
import com.zxf.basic.http.RetrofitHelper
import com.zxf.basic.utils.PageState
import io.reactivex.disposables.Disposable
import retrofit2.Call

open class BaseViewModel : ViewModel() {
    private val observerList = mutableListOf<Disposable?>()
    private val callList = mutableListOf<Call<*>>()

    private val _loadState = MutableLiveData<@LoadingState Int>()
    val loadingState = _loadState

    private val _pageState = MutableLiveData<@PageState Int>()
    val pageState = _pageState

    override fun onCleared() {
        super.onCleared()
        callList.forEach {
            if (!it.isCanceled) {
                it.cancel()
            }
        }
        observerList.forEach {
            it?.dispose()
        }
    }

    fun updatePageState(@PageState pageState: Int) {
        _pageState.value = pageState
    }

    fun getObservers(): MutableList<Disposable?> {
        return observerList
    }

    fun getCalls(): MutableList<Call<*>> {
        return callList
    }

    fun <T> getService(clazz: Class<T>): T = RetrofitHelper.get().create(clazz)
}