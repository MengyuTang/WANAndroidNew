package com.tang.alex.wanandroid.presentor

import com.tang.alex.wanandroid.model.LoginModel
import com.tang.alex.wanandroid.model.bean.BaseBean
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
        LoginModel().getData(map,object :MyObservable(){
            override fun subscribeActual(observer: Observer<in BaseBean>?) {

            }

            override fun onSuccess(data: BaseBean) {
                view?.showData(data)
            }

            override fun onFailed(errMsg: String) {
                view?.showErrorMessage(errMsg)
            }

            override fun onError(errMsg: String) {
                view?.showErrorMessage(errMsg)
            }

            override fun onComplete() {
                view?.dismissDialog()
            }

        })
    }

    /**
     * 注册
     */
    fun register(map: HashMap<String, String>) {
        view?.showLoadingDialog()
        LoginModel().register(map,object :MyObservable(){
            override fun subscribeActual(observer: Observer<in BaseBean>?) {

            }

            override fun onSuccess(data: BaseBean) {
                view?.showData(data)
            }

            override fun onFailed(errMsg: String) {
                view?.showErrorMessage(errMsg)
            }

            override fun onError(errMsg: String) {
                view?.showErrorMessage(errMsg)
            }

            override fun onComplete() {
                view?.dismissDialog()
            }

        })
    }

    /**
     * 退出
     */
     fun logout() {
        view?.showLoadingDialog()
        LoginModel().logout(object :MyObservable(){
            override fun subscribeActual(observer: Observer<in BaseBean>?) {

            }

            override fun onSuccess(data: BaseBean) {
                view?.showData(data)
            }

            override fun onFailed(errMsg: String) {
                view?.showErrorMessage(errMsg)
            }

            override fun onError(errMsg: String) {
                view?.showErrorMessage(errMsg)
            }

            override fun onComplete() {
                view?.dismissDialog()
            }

        })
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