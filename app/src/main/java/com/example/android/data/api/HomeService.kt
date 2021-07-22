package com.example.android.data.api

import com.example.android.data.bean.ArticleData
import com.example.android.data.bean.BannerInfo
import com.example.android.http.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {

    /**
     * 获取首页文章列表
     */
    @GET("article/list/{pageIndex}/json")
    fun getArticleInfo(@Path("pageIndex") pageIndex: Int): Call<BaseResponse<ArticleData>>

    /**
     * 获取首页banner
     */
    @GET("banner/json")
    fun getBanner(): Call<BaseResponse<List<BannerInfo>>>
}