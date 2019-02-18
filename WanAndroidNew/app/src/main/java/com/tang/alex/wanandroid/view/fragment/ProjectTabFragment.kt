package com.tang.alex.wanandroid.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.adapter.ProjectsAdapter
import com.tang.alex.wanandroid.model.bean.ArticleBean
import kotlinx.android.synthetic.main.fragment_tab.*

class ProjectTabFragment: Fragment() {

    private var adapter: ProjectsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab,container,false)
    }

    fun setDatas(articleInfos:ArrayList<ArticleBean>){
        adapter = ProjectsAdapter(context!!)
        ry_knowledge.layoutManager = LinearLayoutManager(context!!)
        ry_knowledge.adapter = adapter
        adapter!!.setDatas(articleInfos)
        knowledge_toTop.setOnClickListener {
            ry_knowledge.smoothScrollToPosition(0)
        }
    }

    fun smoothScrollToTop(){
        ry_knowledge.smoothScrollToPosition(0)
    }
}