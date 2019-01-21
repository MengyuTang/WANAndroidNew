package com.tang.alex.wanandroid.presentor

import com.tang.alex.wanandroid.view.IView

interface IPresentor{
    fun getData(map:HashMap<String,String>)

    fun onAttachView(view:IView)

    fun onDetachView()
}
