package com.example.android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.data.api.HomeService
import com.example.android.data.bean.ArticleData
import com.example.android.data.bean.BannerInfo
import com.example.android.data.repository.HomeRepository
import com.example.android.http.Result
import com.zxf.basic.base.BaseViewModel
import com.zxf.basic.utils.ToastUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    private val mService = getService(HomeService::class.java)
    private val mHomeRepository = HomeRepository(mService)

    private val _articleData = MutableLiveData<ArticleData>()

    fun getArticleInfo(pageIndex: Int): LiveData<ArticleData> {
        viewModelScope.launch {
            mHomeRepository.getArticleInfo(pageIndex)
                .onStart {
                    ToastUtils.show("请求开始")
                }
                .collectLatest { result ->
                   when (result) {
                       is Result.Success<*> -> {
                           _articleData.value = (result as Result.Success<ArticleData>).data
                       }
                       is Result.Error -> {
                           ToastUtils.show(result.msg)
                       }
                       is Result.Complete -> {
                           ToastUtils.show("请求结束")
                       }
                   }
                }
        }
        return _articleData
    }

    fun getBannerInfo(): LiveData<List<BannerInfo>> {
        viewModelScope.launch {
            mHomeRepository.getBannerInfo()
                .asLiveData()
        }
    }
}