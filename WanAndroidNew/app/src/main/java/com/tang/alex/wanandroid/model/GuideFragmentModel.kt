package com.tang.alex.wanandroid.model

import com.tang.alex.wanandroid.utils.MyObserver
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.utils.network.RetrofitManager

class GuideFragmentModel:IModel {
    override fun getData(map: HashMap<String, String>, observable: MyObservable) {
    }

    fun getNaviData(observable: MyObservable){
        RetrofitManager().getNaviData(MyObserver(observable))
    }
}