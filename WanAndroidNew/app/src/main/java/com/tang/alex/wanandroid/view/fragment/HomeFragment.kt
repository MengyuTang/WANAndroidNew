package com.tang.alex.wanandroid.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.adapter.HomeAdapter
import com.tang.alex.wanandroid.model.bean.ArticleBean
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.presentor.HomeFragmentPresentor
import com.tang.alex.wanandroid.view.IView
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * 首页
 */
class HomeFragment: Fragment(), IView {
    private val TAG = "HomeFragment"

    /**
     * 图片地址集合
     */
    var images = ArrayList<String>()

    /**
     * 图片对应链接集合
     */
    var imageLink = ArrayList<String>()

    /**
     * 图片描述集合
     */
    var titles = ArrayList<String>()

    private val mPresentor = HomeFragmentPresentor()

    private var homeAdapter:HomeAdapter? = null

    /**
     * 当前页码
     */
    private var mPage = 0

    private var articleList = ArrayList<ArticleBean>()

    private var mLoadMore = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    fun initView() {
        mPresentor.onAttachView(this)
        getBanners()
        getArticles()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        ry_home_articles.layoutManager = layoutManager
        homeAdapter = HomeAdapter(activity!!)
        ry_home_articles.adapter = homeAdapter
        ry_home_articles.setHasFixedSize(true)
        ry_home_articles.setLoadingMoreEnabled(true)
        ry_home_articles.setPullRefreshEnabled(true)
        ry_home_articles.setLoadingListener(object:XRecyclerView.LoadingListener{
            override fun onLoadMore() {
                mPage++
                mLoadMore = true
                getArticles()
            }

            override fun onRefresh() {
                mLoadMore = false
                mPage = 0
                getArticles()
            }

        })
    }

    /**
     * 获取banner图片集合
     */
    private fun getBanners() {
        mPresentor.getBanners()
    }

    /**
     * 获取文章列表
     */
    private fun getArticles() {
        val map = HashMap<String,String>()
        map["page"] = mPage.toString()
        mPresentor.getArticles(map)
    }

    override fun showLoadingDialog() {
        Log.e(TAG,"showLoadingDialog!!!")
    }

    override fun dismissDialog() {
        Log.e(TAG,"dismissDialog!!!")
        ry_home_articles.loadMoreComplete()
        ry_home_articles.refreshComplete()
    }

    override fun showData(data: BaseBean,type:String) {
        ry_home_articles.loadMoreComplete()
        ry_home_articles.refreshComplete()
        if (type == "articles"){
            val articleData = data.data as Map<*,*>
            val articles:List<ArticleBean> = articleData["datas"] as List<ArticleBean>
            if (mLoadMore){
                if (articles.isNotEmpty()){
                    articleList.addAll(articles)
                }
            }else{
                articleList.clear()
                articleList.addAll(articles)
            }
        }else if (type == "banners"){
            val banners:List<Any> = data.data as List<Any>
            if (banners.isNotEmpty()){
                for (bannerBean in banners){
                    val map = bannerBean as Map<String,String>
                    images.add(map["imagePath"]!!)
                    titles.add(map["title"]!!)
                    imageLink.add(map["url"]!!)
                }
            }
        }
        if(images.isNotEmpty() && articleList.isNotEmpty()){
            homeAdapter!!.setDatas(images,titles,imageLink,articleList)
        }
    }

    override fun showErrorMessage(errMsg: String) {
        Toast.makeText(activity,errMsg,Toast.LENGTH_SHORT).show()
    }
}