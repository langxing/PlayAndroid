package com.example.android.ui.home

import androidx.lifecycle.*
import com.example.android.data.api.HomeService
import com.example.android.data.bean.ArticleData
import com.example.android.data.bean.BannerInfo
import com.example.android.data.repository.HomeRepository
import com.example.login.data.http.Result
import com.zxf.basic.base.BaseViewModel
import com.zxf.basic.utils.ToastUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    private val mService = getService(HomeService::class.java)
    private val mHomeRepository = HomeRepository(mService)

    fun getArticleInfo(pageIndex: Int): LiveData<ArticleData> {
        val articleData = MutableLiveData<ArticleData>()
        viewModelScope.launch {
            mHomeRepository.getArticleInfo(pageIndex)
                .onStart {
                    ToastUtils.show("请求开始")
                }
                .collectLatest { result ->
                   when (result) {
                       is Result.Success -> {
                           articleData.value = result.data
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
        return articleData
    }

    fun getBannerInfo() : LiveData<List<BannerInfo>> {
        val data = MutableLiveData<List<BannerInfo>>()
        viewModelScope.launch {
            mHomeRepository.getBannerInfo()
                .collectLatest { result ->
                    when (result) {
                        is Result.Success -> data.value = result.data
                        is Result.Error -> {
                            ToastUtils.show(result.msg)
                        }
                        is Result.Complete -> {
                            ToastUtils.show("请求结束")
                        }
                    }
                }
        }
        return data
    }
}