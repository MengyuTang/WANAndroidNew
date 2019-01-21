package com.tang.alex.wanandroid.model

import android.util.Log
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.utils.network.RetrofitManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class LoginModel:IModel{
    /**
     * 登录
     */
    override fun getData(map: HashMap<String, String>,observable: MyObservable) {
        RetrofitManager().login(map,object :Observer<BaseBean>{
            override fun onComplete() {
                observable.onComplete()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: BaseBean) {
                if (t.errorCode == 0){
                    observable.onSuccess(t)
                }else{
                    Log.e("LoginModel",t.errorMsg)
                    observable.onFailed(t.errorMsg)
                }
            }

            override fun onError(e: Throwable) {
                Log.e("LoginModel",e.message!!)
                observable.onError(e.message!!)
            }

        })
    }

    /**
     * 注册
     */
     fun register(map: HashMap<String, String>,observable: MyObservable) {
        RetrofitManager().register(map,object :Observer<BaseBean>{
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
                observable.onError(e.message!!)
            }

        })
    }

    /**
     * 退出
     */
     fun logout(observable: MyObservable) {
        RetrofitManager().logout(object :Observer<BaseBean>{
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
                observable.onError(e.message!!)
            }

        })
    }
}