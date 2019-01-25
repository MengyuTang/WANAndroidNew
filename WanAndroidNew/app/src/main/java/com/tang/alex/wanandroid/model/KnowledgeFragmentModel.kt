package com.tang.alex.wanandroid.model

import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.MyObserver
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.utils.network.RetrofitManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class KnowledgeFragmentModel:IModel {
    override fun getData(map: HashMap<String, String>, observable: MyObservable) {
    }

    /**
     * 获取知识体系
     */
    fun getKnowledgeTree(observable: MyObservable){
        RetrofitManager().getKnowledgeTree(MyObserver(observable))
    }

    /**
     * 获取知识体系下的文章
     */
    fun getTreeArticles(page:String,cid:String,observable: MyObservable){
        RetrofitManager().getTreeArticles(page,cid,MyObserver(observable))
    }

}