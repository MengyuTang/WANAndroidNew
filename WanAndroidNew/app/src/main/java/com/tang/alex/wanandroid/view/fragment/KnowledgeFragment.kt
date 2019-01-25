package com.tang.alex.wanandroid.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.adapter.KnowledgeAdapter
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.model.bean.KnowledgeInfo
import com.tang.alex.wanandroid.presentor.KnowledgeFragmentPresentor
import com.tang.alex.wanandroid.view.IView
import kotlinx.android.synthetic.main.fragment_knowledge.*
import java.math.BigDecimal


/**
 * 知识体系
 */
class KnowledgeFragment: Fragment(), IView {
    private val TAG = "KnowledgeFragment"

    private val mPresentor = KnowledgeFragmentPresentor()

    private var mPage = 0

    private var trees = ArrayList<String>()

    private var childrenTree = HashMap<String,List<KnowledgeInfo>>()

    private var treeAdapter:KnowledgeAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_knowledge,container,false)
    }

    fun initView() {
        mPresentor.onAttachView(this)
        getTree()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        var layoutManager = LinearLayoutManager(activity)
        ry_knowledge.layoutManager = layoutManager
        treeAdapter = KnowledgeAdapter(activity!!)
        ry_knowledge.adapter = treeAdapter
    }

    /**
     * 获取知识体系
     */
    private fun getTree() {
        mPresentor.getKnowledgeTree()
    }

    /**
     * 获取知识体系下的文章
     */
    private fun getTreeArticles(cid:String) {
        mPresentor.getTreeArticles(mPage.toString(),cid)
    }

    override fun showLoadingDialog() {
        Log.e(TAG,"showLoadingDialog!!!")
    }

    override fun dismissDialog() {
        Log.e(TAG,"dismissDialog!!!")
    }

    override fun showData(data: BaseBean,type:String) {
        if (type == "tree"){
            val tree = data.data as List<*>
            trees.clear()
            for (treeItem in tree){
                val map = treeItem as Map<String,Any>
                trees.add(map["name"].toString())
                val children = map["children"] as List<*>
                val list = ArrayList<KnowledgeInfo>()
                for (child in children){
                    val mapInList = child as Map<*,*>
                    list.add(KnowledgeInfo(mapInList["name"].toString(), BigDecimal(mapInList["id"].toString()).toInt()))
                }
                childrenTree[map["name"].toString()] = list
            }
            treeAdapter!!.setDatas(trees,childrenTree)
        }else if (type == "treeArticles"){

        }
    }

    override fun showErrorMessage(errMsg: String) {
        Toast.makeText(activity,errMsg,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresentor.onDetachView()
    }
}