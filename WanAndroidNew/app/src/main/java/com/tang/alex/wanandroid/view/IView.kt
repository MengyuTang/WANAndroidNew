package com.tang.alex.wanandroid.view

import com.tang.alex.wanandroid.model.bean.BaseBean

interface IView{
    fun showLoadingDialog()

    fun dismissDialog()

    fun showData(data:BaseBean,type:String)

    fun showErrorMessage(errMsg:String)
}
