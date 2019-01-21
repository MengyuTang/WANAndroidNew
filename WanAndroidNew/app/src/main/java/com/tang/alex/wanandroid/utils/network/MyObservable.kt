package com.tang.alex.wanandroid.utils.network

import com.tang.alex.wanandroid.model.bean.BaseBean

import io.reactivex.Observable

abstract class MyObservable : Observable<BaseBean>() {
    abstract fun onSuccess(data: BaseBean)

    abstract fun onFailed(errMsg: String)

    abstract fun onError(errMsg: String)

    abstract fun onComplete()

}
