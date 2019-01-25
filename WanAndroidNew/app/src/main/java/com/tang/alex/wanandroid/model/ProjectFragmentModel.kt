package com.tang.alex.wanandroid.model

import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.MyObserver
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.utils.network.RetrofitManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class ProjectFragmentModel:IModel {
    override fun getData(map: HashMap<String, String>, observable: MyObservable) {
    }

    fun getProjectTree(observable: MyObservable){
        RetrofitManager().getProjectTree(MyObserver(observable))
    }

    fun getProjectList(page:String,cid:String,observable: MyObservable){
        RetrofitManager().getProjectList(page,cid,MyObserver(observable))
    }
}