package com.tang.alex.wanandroid.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.model.bean.ArticleBean
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.model.bean.NaviBean
import com.tang.alex.wanandroid.presentor.GuideFragmentPresentor
import com.tang.alex.wanandroid.view.IView


/**
 * 导航
 */
class GuideFragment: Fragment(), IView {
    private val TAG = "GuideFragment"

    private val mPresentor = GuideFragmentPresentor()

    private var navis = ArrayList<NaviBean>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_guide,container,false)
    }

    fun initView() {
        mPresentor.onAttachView(this)
        getNaviData()
    }

    private fun getNaviData() {
        mPresentor.getNaviData()
    }

    override fun showLoadingDialog() {
        Log.e(TAG,"showLoadingDialog!!!")
    }

    override fun dismissDialog() {
        Log.e(TAG,"dismissDialog!!!")
    }

    override fun showData(data: BaseBean,type:String) {
        val datas = data.data as List<*>
        for (navi in datas){
            val map = navi as Map<*,*>
            val naviBean = NaviBean(map["articles"] as List<ArticleBean>, map["cid"].toString(),map["name"].toString())
            navis.add(naviBean)
        }
        Log.e(TAG,"navis:"+Gson().toJson(navis))
    }

    override fun showErrorMessage(errMsg: String) {
        Toast.makeText(activity,errMsg,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresentor.onDetachView()
    }
}