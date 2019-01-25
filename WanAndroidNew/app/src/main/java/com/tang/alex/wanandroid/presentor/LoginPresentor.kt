package com.tang.alex.wanandroid.presentor

import com.tang.alex.wanandroid.model.LoginModel
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.utils.MyObservalbeImpl
import com.tang.alex.wanandroid.utils.network.MyObservable
import com.tang.alex.wanandroid.view.IView
import io.reactivex.Observer

class LoginPresentor:IPresentor {

    private var view:IView? = null

    /**
     * 登录
     */
    override fun getData(map: HashMap<String, String>) {
        view?.showLoadingDialog()
        LoginModel().getData(map,MyObservalbeImpl(view!!,"login"))
    }

    /**
     * 注册
     */
    fun register(map: HashMap<String, String>) {
        view?.showLoadingDialog()
        LoginModel().register(map,MyObservalbeImpl(view!!,"register"))
    }

    /**
     * 退出
     */
     fun logout() {
        view?.showLoadingDialog()
        LoginModel().logout(MyObservalbeImpl(view!!,"logout"))
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