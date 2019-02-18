package com.tang.alex.wanandroid.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
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
import com.tang.alex.wanandroid.presentor.ProjectFragmentPresentor
import com.tang.alex.wanandroid.view.IView
import kotlinx.android.synthetic.main.fragment_projects.*
import java.math.BigDecimal


/**
 * 项目
 */
class ProjectsFragment: Fragment(), IView {
    private val TAG = "ProjectsFragment"

    private val mPresentor = ProjectFragmentPresentor()

    private var trees = ArrayList<KnowledgeTreeBean>()

    private var mPage = 0

    private var articleInfos = ArrayList<ArticleBean>()

    var listFragments = ArrayList<ProjectTabFragment>()

    private var contentAdapter:ContentPagerAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_projects,container,false)
    }

    fun initView() {
        mPresentor.onAttachView(this)
        getProjectTree()
    }

    private fun initTab(){
        project_tab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position:Int = project_tab.selectedTabPosition
                val item = trees[position]
                projects_vPager.currentItem = position
                mPresentor.getProjectList(mPage.toString(),item.id.toString())
            }
        })

        listFragments.clear()
        for (item in trees){
            val tab = project_tab.newTab()
            tab.text = item.name
            project_tab.addTab(tab)
            val fragment = ProjectTabFragment()
            val bundle = Bundle()
            bundle.putString("item",item.name)
            fragment.arguments = bundle
            listFragments.add(fragment)
        }
        project_tab.setTabTextColors(ContextCompat.getColor(activity!!,R.color.color_light_gray), ContextCompat.getColor(activity!!,R.color.white))
        project_tab.setSelectedTabIndicatorColor(ContextCompat.getColor(activity!!, R.color.white))
        contentAdapter = ContentPagerAdapter(childFragmentManager)
        projects_vPager.adapter = contentAdapter
        projects_vPager.offscreenPageLimit = listFragments.size
        projects_vPager.currentItem = 0
        projects_vPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(project_tab))
    }

    private fun initRecyclerView() {
        if (articleInfos.isNotEmpty()) {
            val article = articleInfos[0] as Map<Any,Any>
            val chapterName = article["chapterName"].toString()
            for (fragment in listFragments){
                val item = fragment.arguments!!.getSerializable("item").toString()
                if (chapterName == item){
                    fragment.setDatas(articleInfos)
                    contentAdapter!!.notifyDataSetChanged()
                }
            }
        }
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
        if (type == "tree") {
            trees.clear()
            val list = data.data as List<*>
            Log.e(TAG,"data:"+Gson().toJson(data))
            for (item in list){
                val map = item as Map<*,*>
                val bean = KnowledgeTreeBean(BigDecimal(map["id"].toString()).toInt(),map["name"].toString())
                trees.add(bean)
            }
            val msg = Message.obtain()
            msg.what = 1
            mHandler.sendMessage(msg)
            Log.e(TAG,"trees:"+Gson().toJson(trees))
        }else if (type == "list") {
            val articleSumBean = data.data as Map<*, *>
            if (articleSumBean.isNotEmpty()){
                articleInfos.clear()
                articleInfos = articleSumBean["datas"] as ArrayList<ArticleBean>
            }
            val msg = Message.obtain()
            msg.what = 2
            mHandler.sendMessage(msg)
        }

    }

    override fun showErrorMessage(errMsg: String) {
        Toast.makeText(activity,errMsg,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresentor.onDetachView()
    }

    private val callback = Handler.Callback{
        if (it.what == 1){
            initTab()
        }else if (it.what == 2){
            initRecyclerView()
        }
        return@Callback true
    }

    private val mHandler = Handler(callback)

    fun backToTop(){
        val pos = project_tab.selectedTabPosition
        listFragments[pos].smoothScrollToTop()
    }

    inner class ContentPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return listFragments[position]
        }

        override fun getCount(): Int {
            return listFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return trees[position].name
        }
    }
}
