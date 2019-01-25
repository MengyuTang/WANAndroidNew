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
     * 获取首页banner
     */
    @GET("/banner/json")
    fun getBanners():Observable<BaseBean>

    /**
     * 获取常用网站
     */
    @GET("/friend/json")
    fun getCommonWebs():Observable<BaseBean>

    /**
     * 获取搜索热词
     */
    @GET("/hotkey/json")
    fun getHotKeys():Observable<BaseBean>

    /**
     * 关键字搜索
     */
    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    fun searchArticles(@Path("page")page:String, @Field(value = "k")k:String):Observable<BaseBean>

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

    /**
     * 获取知识体系
     */
    @GET("/tree/json")
    fun getKnowledgeTree():Observable<BaseBean>

    /**
     * 获取知识体系下的文章
     */
    @GET("/article/list/{page}/json")
    fun getTreeArticles(@Path("page")page:String,@Query("cid")cid:String):Observable<BaseBean>

    /**
     * 获取导航数据
    */
    @GET("/navi/json")
    fun getNaviData():Observable<BaseBean>

    /**
     * 获取项目分类
    */
    @GET("/project/tree/json")
    fun getProjectTree():Observable<BaseBean>

    /**
     * 获取项目分类
    */
    @GET("/project/list/{page}/json")
    fun getProjectList(@Path("page")page:String,@Query("cid")cid:String):Observable<BaseBean>

    /**
     * 获取收藏文章列表
    */
    @GET("/lg/collect/list/{page}/json")
    fun getCollectList(@Path("page")page:String):Observable<BaseBean>

    /**
     * 收藏站内文章
    */
    @FormUrlEncoded
    @POST("/lg/collect/{id}/json")
    fun collectArticle(@Path("id")page:String):Observable<BaseBean>

    /**
     * 取消收藏(文章列表中)
    */
    @FormUrlEncoded
    @POST("/lg/uncollect_originId/{id}/json")
    fun unCollectArticle_origin(@Path("id")id:String):Observable<BaseBean>

    /**
     * 取消收藏（我的收藏中）
    */
    @FormUrlEncoded
    @POST("/lg/uncollect/{id}/json")
    fun unCollectArticle(@Path("id")id:String,@FieldMap map:Map<String,String>):Observable<BaseBean>
    /**
     * 收藏网站列表
    */
    @GET("/lg/collect/usertools/json")
    fun getCollectWebs(@Path("id")id:String):Observable<BaseBean>
    /**
     * 收藏网站
    */
    @FormUrlEncoded
    @POST("/lg/collect/addtool/json")
    fun collectWeb(@FieldMap map:Map<String,String>):Observable<BaseBean>
    /**
     * 编辑收藏网站
    */
    @FormUrlEncoded
    @POST("/lg/collect/updatetool/json")
    fun updateWeb(@FieldMap map:Map<String,String>):Observable<BaseBean>
    /**
     * 删除收藏网站
    */
    @FormUrlEncoded
    @POST("/lg/collect/deletetool/json")
    fun deleteWeb(@FieldMap map:Map<String,String>):Observable<BaseBean>
}