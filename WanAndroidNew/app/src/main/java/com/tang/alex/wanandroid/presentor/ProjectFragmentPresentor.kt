package com.tang.alex.wanandroid.presentor

import com.tang.alex.wanandroid.model.ProjectFragmentModel
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.MyObservalbeImpl
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.view.IView
import io.reactivex.Observer

class ProjectFragmentPresentor:IPresentor {

    private var view:IView? = null
    override fun getData(map: HashMap<String, String>) {
    }

    /**
     * 获取项目分类
     */
    fun getProjectTree(){
        view!!.showLoadingDialog()
        ProjectFragmentModel().getProjectTree(MyObservalbeImpl(view!!,"tree"))
    }

    /**
     * 获取项目列表
     */
    fun getProjectList(page:String,cid:String){
        view!!.showLoadingDialog()
        ProjectFragmentModel().getProjectList(page,cid,MyObservalbeImpl(view!!,"list"))
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