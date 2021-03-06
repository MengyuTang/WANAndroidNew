package com.tang.alex.wanandroid.view.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.adapter.KnowledgeDetailAdapter
import com.tang.alex.wanandroid.model.bean.ArticleBean
import kotlinx.android.synthetic.main.fragment_tab.*

class TabFragment: Fragment() {

    private var adapter: KnowledgeDetailAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_tab,container,false)
    }

    fun setDatas(articleInfos:ArrayList<ArticleBean>){
        adapter = KnowledgeDetailAdapter(activity!!)
        ry_knowledge.layoutManager = LinearLayoutManager(activity!!)
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