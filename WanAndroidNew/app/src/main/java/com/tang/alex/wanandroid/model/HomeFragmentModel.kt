package com.tang.alex.wanandroid.model

import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.MyObserver
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.utils.network.RetrofitManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class HomeFragmentModel:IModel {
    override fun getData(map: HashMap<String, String>, observable: MyObservable) {

    }

    fun getArticles(map: HashMap<String, String>, observable: MyObservable) {
        RetrofitManager().getFirstPageArticles(map["page"]!!, MyObserver(observable))
    }

    fun getBanners(observable: MyObservable) {
        RetrofitManager().getBanners(MyObserver(observable))
    }

}