package com.tang.alex.wanandroid.utils.network

import com.tang.alex.wanandroid.model.bean.BaseBean
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager {
    private val baseUrl = "http://www.wanandroid.com/"

    private var okHttpClient = OkHttpClient.Builder()
            .readTimeout(8, TimeUnit.SECONDS)
            .connectTimeout(8, TimeUnit.SECONDS)
            .addInterceptor(LoggingInterceptor())
            .build()

    private var apiService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)

    /**
     * 注册
     */
    fun register(map:HashMap<String,String>, observer: Observer<BaseBean>){
        apiService.register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * 登录
     */
    fun login(map:HashMap<String,String>, observer: Observer<BaseBean>){
        apiService.login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * 退出
     */
    fun logout(observer: Observer<BaseBean>){
        apiService.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * 获取首页文章列表
     */
    fun getFirstPageArticles(page:String,observer: Observer<BaseBean>){
        apiService.getFirstPageArticles(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}