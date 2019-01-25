package com.tang.alex.wanandroid.presentor

import com.tang.alex.wanandroid.model.HomeFragmentModel
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.MyObservalbeImpl
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.view.IView
import io.reactivex.Observer

open class HomeFragmentPresentor:IPresentor {
    private var view:IView? = null
    override fun getData(map: HashMap<String, String>) {

    }

    /**
     * 获取首页文章列表
     */
    fun getArticles(map: HashMap<String, String>){
        view!!.showLoadingDialog()
        HomeFragmentModel().getArticles(map,MyObservalbeImpl(view!!,"articles"))
    }

    /**
     * 获取首页banner
     */
    fun getBanners(){
        view!!.showLoadingDialog()
        HomeFragmentModel().getBanners(MyObservalbeImpl(view!!,"banners"))
    }

    override fun onAttachView(view: IView) {
        this.view = view
    }

    override fun onDetachView() {
        if (null != view){
            view = null
        }
    }

}