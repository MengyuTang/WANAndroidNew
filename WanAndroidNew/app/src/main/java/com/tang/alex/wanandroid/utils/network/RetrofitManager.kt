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

    /**
     * 获取首页banner
     */
    fun getBanners(observer: Observer<BaseBean>){
        apiService.getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * 获取热门搜索
     */
    fun getHotKeys(observer: Observer<BaseBean>){
        apiService.getHotKeys()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * 获取常用网站列表
     */
    fun getCommonWebs(observer: Observer<BaseBean>){
        apiService.getCommonWebs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
    /**
     * 关键字搜索
     */
    fun searchArticles(page:String,k:String,observer: Observer<BaseBean>){
        apiService.searchArticles(page,k)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
    /**
     * 获取知识体系
     */
    fun getKnowledgeTree(observer: Observer<BaseBean>){
        apiService.getKnowledgeTree()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * 获取知识体系下的文章
     */
    fun getTreeArticles(page:String,cid:String,observer: Observer<BaseBean>){
        apiService.getTreeArticles(page,cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * 获取导航数据
     */
    fun getNaviData(observer: Observer<BaseBean>){
        apiService.getNaviData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * 获取项目分类
     */
    fun getProjectTree(observer: Observer<BaseBean>){
        apiService.getProjectTree()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * 获取项目列表数据
     */
    fun getProjectList(page:String,cid:String,observer: Observer<BaseBean>){
        apiService.getProjectList(page,cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}