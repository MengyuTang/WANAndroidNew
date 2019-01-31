package com.tang.alex.wanandroid.view.activity

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.model.bean.ArticleBean
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.presentor.KnowledgeFragmentPresentor
import com.tang.alex.wanandroid.view.IView
import com.tang.alex.wanandroid.view.fragment.TabFragment
import kotlinx.android.synthetic.main.activity_knowledge_detail.*

class KnowledgeDetailActivity : AppCompatActivity(),IView {

    private var TAG = "KnowledgeDetailActivity"

    private var articleInfos = ArrayList<ArticleBean>()

    private var articleInfoMap = HashMap<String,ArrayList<ArticleBean>>()

    private var currentActivity:KnowledgeDetailActivity? = null

    private var listName:ArrayList<String>? = null

    private var articleMap: HashMap<String,Int>? = null
    var listFragments = ArrayList<TabFragment>()

    private var contentAdapter:ContentPagerAdapter? = null

    private val mPresentor = KnowledgeFragmentPresentor()

    private var mPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledge_detail)
        val localLayoutParams = window.attributes
        localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
        initView()
    }

    private fun initView(){
        currentActivity = this
        mPresentor.onAttachView(this)
        initToolBar()
        initTab()
        initViewPager()
    }

    private fun initToolBar(){
        knowledge_toolBar.title = intent.getStringExtra("title")
        Log.e(TAG,"title:$title")
        knowledge_toolBar.setTitleTextColor(Color.WHITE)
        knowledge_toolBar.navigationIcon = resources.getDrawable(R.mipmap.back)
        setSupportActionBar(knowledge_toolBar)
        knowledge_toolBar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initTab(){
        listName = intent.getSerializableExtra("name") as ArrayList<String>
        articleMap = intent.getSerializableExtra("articleMap") as HashMap<String,Int>
        knowledge_tab.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                knowledge_vPager.currentItem = knowledge_tab.selectedTabPosition
            }
        })
        listFragments.clear()
        for (item in listName!!){
            val tab = knowledge_tab.newTab()
            tab.text = item
            knowledge_tab.addTab(tab)
            val fragment = TabFragment()
            val bundle = Bundle()
            bundle.putString("item",item)
            fragment.arguments = bundle
            listFragments.add(fragment)
        }
        knowledge_tab.setSelectedTabIndicatorColor(ContextCompat.getColor(currentActivity!!, R.color.white))
        knowledge_tab.setTabTextColors(ContextCompat.getColor(currentActivity!!,R.color.color_light_gray),ContextCompat.getColor(currentActivity!!,R.color.white))
        knowledge_tab.setupWithViewPager(knowledge_vPager,true)
        contentAdapter = ContentPagerAdapter(supportFragmentManager)
        knowledge_vPager.adapter = contentAdapter
    }

    private fun initViewPager(){
        knowledge_vPager.offscreenPageLimit = listFragments.size
        knowledge_vPager.currentItem = 0
        initDatas(0)
        knowledge_vPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                initDatas(position)
            }
        })
    }

    fun initDatas(position:Int){
        var text = listName!![position]
        val id = articleMap!![text]!!
        if (null == articleInfoMap[text]){
            mPresentor.getTreeArticles(mPage.toString(),id.toString())
        }else{
            articleInfos = articleInfoMap[text]!!
            for (fragment in listFragments){
                val item = fragment.arguments!!.getSerializable("item").toString()
                if (text == item){
                    fragment.setDatas(articleInfos)
                    contentAdapter!!.notifyDataSetChanged()
                }
            }
        }
    }

    inner class ContentPagerAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return listFragments[position]
        }

        override fun getCount(): Int {
            return listFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return listName!![position]
        }
    }

    override fun showLoadingDialog() {
        Log.e(TAG,"showLoadingDialog!!!")
    }

    override fun dismissDialog() {
        Log.e(TAG,"dismissDialog!!!")
    }

    override fun showData(data: BaseBean, type: String) {
        val map = data.data as Map<*,*>
        articleInfos = map["datas"] as ArrayList<ArticleBean>
        val article = articleInfos[0] as Map<*,*>
        val chapterName = article["chapterName"].toString()
        articleInfoMap[chapterName] = articleInfos
        for (fragment in listFragments){
            val item = fragment.arguments!!.getSerializable("item").toString()
            if (chapterName == item){
                fragment.setDatas(articleInfos)
                contentAdapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun showErrorMessage(errMsg: String) {
        Toast.makeText(this,errMsg,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresentor.onDetachView()
    }
}
