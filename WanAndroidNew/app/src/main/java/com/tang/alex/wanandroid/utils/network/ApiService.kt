package com.tang.alex.wanandroid.utils.network

import com.tang.alex.wanandroid.model.bean.BaseBean
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    /**
     * 获取首页文章列表
     */
    @GET("/article/list/{page}/json")
    fun getFirstPageArticles(@Path("page")page:String):Observable<BaseBean>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun login(@FieldMap map:Map<String,String>):Observable<BaseBean>
    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/user/register")
    fun register(@FieldMap map:Map<String,String>):Observable<BaseBean>
    /**
     * 退出
     */
    @GET("/user/logout/json")
    fun logout():Observable<BaseBean>
}