package com.tang.alex.wanandroid.utils

import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.view.IView
import io.reactivex.Observer

class MyObservalbeImpl(private val view:IView,private val type:String):MyObservable() {
    override fun onSuccess(data: BaseBean) {
        view.showData(data,type)
    }

    override fun onFailed(errMsg: String) {
        view.showErrorMessage(errMsg)
    }

    override fun onError(errMsg: String) {
        view.showErrorMessage(errMsg)
    }

    override fun onComplete() {
        view.dismissDialog()
    }

    override fun subscribeActual(observer: Observer<in BaseBean>?) {
    }
}