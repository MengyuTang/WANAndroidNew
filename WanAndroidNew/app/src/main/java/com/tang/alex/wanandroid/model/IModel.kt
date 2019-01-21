package com.tang.alex.wanandroid.model

import com.tang.alex.wanandroid.utils.network.MyObservable

interface IModel{
    fun getData(map:HashMap<String,String>,observable: MyObservable)
}
