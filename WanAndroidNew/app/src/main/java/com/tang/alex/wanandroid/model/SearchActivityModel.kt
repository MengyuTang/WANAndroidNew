package com.tang.alex.wanandroid.model

import com.tang.alex.wanandroid.utils.MyObserver
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.utils.network.RetrofitManager

class SearchActivityModel:IModel {
    override fun getData(map: HashMap<String, String>, observable: MyObservable) {

    }

    /**
     * 获取热门搜索
     */
    fun getHotKeys(observable: MyObservable) {
        RetrofitManager().getHotKeys(MyObserver(observable))
    }

    /**
     * 获取常用网站
     */
    fun getCommonWebs(observable: MyObservable) {
        RetrofitManager().getCommonWebs(MyObserver(observable))
    }

    /**
     * 关键字搜索
     */
    fun searchArticles(page:String,k:String,observable: MyObservable) {
        RetrofitManager().searchArticles(page,k,MyObserver(observable))
    }
}