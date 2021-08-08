package com.zxf.basic.http

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelper private constructor() {
    private var retrofit: Retrofit? = null
    private val retrofitMap = mutableMapOf<String, Retrofit>()

    private var  okHttpClient = okHttpClient {
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        addInterceptor(CustomInterceptor())
        readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
        connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
    }

    companion object {
        private const val READ_TIME_OUT = 10 * 1000L
        private const val CONNECT_TIME_OUT = 10 * 1000L

        private val instance: RetrofitHelper by lazy {
            RetrofitHelper()
        }

        fun get() = instance
    }

    /**
     * 全局默认URL
     */
    fun setUrl(url: String) {
        retrofit = retrofit {
            client(okHttpClient)
            baseUrl(url)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }
        retrofitMap[url] = retrofit!!
    }

    /**
     * 新的URL
     */
    fun getRetrofit(url: String): Retrofit {
        retrofit = retrofitMap[url]
        if (retrofit == null) {
            retrofit = retrofit {
                client(okHttpClient)
                baseUrl(url)
                addConverterFactory(GsonConverterFactory.create())
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            }
            retrofitMap[url] = retrofit!!
        }
        return retrofit!!
    }

    fun <T> create(clazz: Class<T>): T {
        return retrofit!!.create(clazz)
    }
}