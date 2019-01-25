package com.tang.alex.wanandroid.presentor

import com.tang.alex.wanandroid.model.SearchActivityModel
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.MyObservalbeImpl
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.view.IView
import io.reactivex.Observer

class SearchActivityPresentor:IPresentor {

    private var view:IView? = null

    override fun getData(map: HashMap<String, String>) {

    }

    /**
     * 获取热门搜索
     */
    fun getHotKeys(){
        view!!.showLoadingDialog()
        SearchActivityModel().getHotKeys(MyObservalbeImpl(view!!,"hotkey"))
    }

    /**
     * 获取热门搜索
     */
    fun getCommonWebs(){
        view!!.showLoadingDialog()
        SearchActivityModel().getCommonWebs(MyObservalbeImpl(view!!,"commonWebs"))
    }

    /**
     * 关键字搜素
     */
    fun searchArticles(page:String,k:String){
        view!!.showLoadingDialog()
        SearchActivityModel().searchArticles(page,k,MyObservalbeImpl(view!!,"searchByKey"))
    }

    override fun onAttachView(view: IView) {
        this.view = view
    }

    override fun onDetachView() {
        if (null != view)
            view = null
    }
}