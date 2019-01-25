package com.tang.alex.wanandroid.presentor

import com.tang.alex.wanandroid.model.KnowledgeFragmentModel
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.MyObservalbeImpl
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.view.IView
import io.reactivex.Observer

class KnowledgeFragmentPresentor:IPresentor {

    private var view:IView? = null
    override fun getData(map: HashMap<String, String>) {
    }

    /**
     * 获取知识体系
     */
    fun getKnowledgeTree(){
        view!!.showLoadingDialog()
        KnowledgeFragmentModel().getKnowledgeTree(MyObservalbeImpl(view!!,"tree"))
    }

    /**
     * 获取知识体系下的文章
     */
    fun getTreeArticles(page:String,cid:String){
        view!!.showLoadingDialog()
        KnowledgeFragmentModel().getTreeArticles(page,cid,MyObservalbeImpl(view!!,"treeArticles"))
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