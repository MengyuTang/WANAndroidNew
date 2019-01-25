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
import com.tang.alex.wanandroid.model.bean.KnowledgeTreeBean
import com.tang.alex.wanandroid.presentor.HomeFragmentPresentor
import com.tang.alex.wanandroid.presentor.ProjectFragmentPresentor
import kotlinx.android.synthetic.main.fragment_home.*
import com.tang.alex.wanandroid.utils.GlideImageLoader
import com.tang.alex.wanandroid.view.IView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer


/**
 * 项目
 */
class ProjectsFragment: Fragment(), IView {
    private val TAG = "ProjectsFragment"

    private val mPresentor = ProjectFragmentPresentor()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_projects,container,false)
    }

    fun initView() {
        mPresentor.onAttachView(this)
        getProjectTree()
    }

    private fun getProjectTree() {
        mPresentor.getProjectTree()
    }

    override fun showLoadingDialog() {
        Log.e(TAG,"showLoadingDialog!!!")
    }

    override fun dismissDialog() {
        Log.e(TAG,"dismissDialog!!!")
    }

    override fun showData(data: BaseBean,type:String) {
        val tree = data.data as List<KnowledgeTreeBean>
        Log.e(TAG,"tree"+Gson().toJson(tree))
    }

    override fun showErrorMessage(errMsg: String) {
        Toast.makeText(activity,errMsg,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresentor.onDetachView()
    }
}