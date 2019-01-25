package com.tang.alex.wanandroid.utils

import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.network.MyObservable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MyObserver(private val observable: MyObservable):Observer<BaseBean> {
    override fun onComplete() {
        observable.onComplete()
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: BaseBean) {
        if (t.errorCode == 0){
            observable.onSuccess(t)
        }else{
            observable.onFailed(t.errorMsg)
        }
    }

    override fun onError(e: Throwable) {
        observable.onFailed(e.message!!)
    }

}