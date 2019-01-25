package com.tang.alex.wanandroid.model

import com.tang.alex.wanandroid.utils.MyObserver
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.utils.network.RetrofitManager

open class LoginModel:IModel{
    /**
     * 登录
     */
    override fun getData(map: HashMap<String, String>,observable: MyObservable) {
        RetrofitManager().login(map,MyObserver(observable))
    }

    /**
     * 注册
     */
     fun register(map: HashMap<String, String>,observable: MyObservable) {
        RetrofitManager().register(map,MyObserver(observable))
    }

    /**
     * 退出
     */
     fun logout(observable: MyObservable) {
        RetrofitManager().logout(MyObserver(observable))
    }
}