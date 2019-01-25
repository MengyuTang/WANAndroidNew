package com.tang.alex.wanandroid.presentor

import com.tang.alex.wanandroid.model.GuideFragmentModel
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.MyObservalbeImpl
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.view.IView
import io.reactivex.Observer

class GuideFragmentPresentor:IPresentor {

    private var view:IView? = null
    override fun getData(map: HashMap<String, String>) {
    }

    fun getNaviData(){
        view!!.showLoadingDialog()
        GuideFragmentModel().getNaviData(MyObservalbeImpl(view!!,"navi"))
    }

    override fun onAttachView(view: IView) {
        this.view = view
    }

    override fun onDetachView() {
        if (null != view){
            view =null
        }
    }
}