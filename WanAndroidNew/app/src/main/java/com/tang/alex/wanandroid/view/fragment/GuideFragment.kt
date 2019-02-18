package com.tang.alex.wanandroid.view.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.adapter.GuideTitleAdapter
import com.tang.alex.wanandroid.model.bean.ArticleBean
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.model.bean.NaviBean
import com.tang.alex.wanandroid.presentor.GuideFragmentPresentor
import com.tang.alex.wanandroid.view.IView
import com.tang.alex.wanandroid.view.activity.BannerDetailActivity
import kotlinx.android.synthetic.main.fragment_guide.*
import kotlinx.android.synthetic.main.item_guide_content.view.*
import kotlinx.android.synthetic.main.item_guide_title.view.*
import java.math.BigDecimal


/**
 * 导航
 */
class GuideFragment: Fragment(), IView {
    private val TAG = "GuideFragment"

    private val mPresentor = GuideFragmentPresentor()

    private var navis = ArrayList<NaviBean>()

    private var titles = ArrayList<String>()

    private var contents = ArrayList<String>()

    private val urlMap = HashMap<String,String>()

    private var titleAdapter: GuideTitleAdapter? = null

    private var guideContentAdapter: GuideContentAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_guide,container,false)
    }

    fun initView() {
        mPresentor.onAttachView(this)
        getNaviData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        refreshTitle()
        refreshContent()
    }

    private fun refreshContent() {
        ry_content_list.layoutManager = LinearLayoutManager(activity)
        guideContentAdapter = GuideContentAdapter(activity!!)
        ry_content_list.adapter = guideContentAdapter
        ry_content_list.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (mShouldScrollContent&& RecyclerView.SCROLL_STATE_IDLE == newState) {
                    mShouldScrollContent = false
                    smoothMoveToPosition(ry_content_list, mToPosition,1)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstPosition = ry_content_list.getChildLayoutPosition(ry_content_list.getChildAt(0))
                with(contents[firstPosition]){
                    if (this.startsWith("title")){
                        var index = titles.indexOf(this.substring(5, this.length))
                        with(index) {
                            if (this != -1) {
                                smoothMoveToPosition(ry_guide_list, this, 2)
                            } else {
                                smoothMoveToPosition(ry_guide_list, this + 1, 2)
                            }
                        }
                    }
                }
            }
        })
    }

    private fun refreshTitle() {
        ry_guide_list.layoutManager = LinearLayoutManager(activity)
        titleAdapter = GuideTitleAdapter(activity!!)
        ry_guide_list.adapter = titleAdapter
        titleAdapter!!.setDatas(navis)
        titleAdapter!!.setMyClickListener(object :GuideTitleAdapter.MyClickListener{
            override fun onClick(position: Int) {
                ry_guide_list.smoothScrollToPosition(position)
            }
        })
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
            val map = navi as Map<String,Any>
            val naviBean = NaviBean(map["articles"] as List<ArticleBean>, BigDecimal(map["cid"].toString()).toInt(),map["name"].toString())
            navis.add(naviBean)
            contents.add("title"+map["name"].toString())
            titles.add(map["name"].toString())
            val articles = map["articles"] as List<*>
            for (article in articles){
                val articleMap = article as Map<String, Any>
                urlMap[articleMap["title"].toString()] = articleMap["link"].toString()
                contents.add(articleMap["title"].toString())
            }
        }
        val msg = Message.obtain()
        msg.what = 1
        mHandler.sendMessage(msg)
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
            titleAdapter!!.setDatas(navis)
            guideContentAdapter!!.setDatas(contents,urlMap)
        }
        return@Callback true
    }

    private val mHandler = Handler(callback)

    class GuideContentAdapter(private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private var articles = ArrayList<String>()
        private var articleUrl = HashMap<String,String>()
        private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

        fun setDatas(articles: ArrayList<String>,articleUrl:HashMap<String,String>) {
            this.articles = articles
            this.articleUrl = articleUrl
            notifyDataSetChanged()
        }

        enum class ITEMTYPE{
            ITEM_TITLE,
            ITEM_CONTENT
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
            if (i == ITEMTYPE.ITEM_TITLE.ordinal){
                return GuideTitleHolder(mLayoutInflater.inflate(R.layout.item_guide_content_title, viewGroup, false))
            }else{
                return GuideContentHolder(mLayoutInflater.inflate(R.layout.item_guide_content, viewGroup, false))
            }
        }

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
            if (articles.size>0){
                if (viewHolder is GuideTitleHolder){
                    viewHolder.itemView.tv_guide.text = articles[position].substring(5,articles[position].length)
                    viewHolder.itemView.tv_guide.textSize = 25f
//                    viewHolder.itemView.tv_guide.setTextColor(Color.parseColor("#333333"))
                }else if (viewHolder is GuideContentHolder){
                    val colors:List<String>  = listOf("#FF4081","#3F51B5","#9a87a7"
                            ,"#59f8fa","#333333")
                    val i:Int = (Math.random()*5).toInt()
                    with(viewHolder.itemView.tv_guide_content){
                        this.text = articles[position]
                        this.setTextColor(Color.parseColor(colors[i]))
                        this.setOnClickListener {
                            //跳转Activity
                            var intent = Intent()
                            intent.setClass(mContext, BannerDetailActivity::class.java)
                            intent.putExtra("title",articles[position])
                            intent.putExtra("url",articleUrl[articles[position]] )
                            mContext.startActivity(intent)
                        }
                    }

                }
            }
        }

        override fun getItemViewType(position: Int): Int {
            if (articles[position].startsWith("title")){
                return ITEMTYPE.ITEM_TITLE.ordinal
            }else{
                return ITEMTYPE.ITEM_CONTENT.ordinal
            }
        }

        override fun getItemCount(): Int {
            return articles.size
        }

        inner class GuideTitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        inner class GuideContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    }

    /**
     * 内容recyclerView是否滑动
     */
    private var mShouldScrollContent:Boolean = false

    /**
     * 标题recyclerView是否滑动
     */
    private var mShouldScrollTitle:Boolean = false

    /**
     * 要滑动到的位置
     */
    private var mToPosition:Int = 0

    /**
     * @param recyclerView 要滑动的recyclerView
     * @param position   滑动目标位置
     * @param type  用来区分是标题recyclerView还是内容recyclerView
     */
    fun smoothMoveToPosition(recyclerView:RecyclerView,position:Int,type:Int){
        var firstVisibleItem = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(0))
        var lastVisibleItem = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(recyclerView.childCount -1))
        if (position< firstVisibleItem){
            recyclerView.smoothScrollToPosition(position)
        }else if (position <= lastVisibleItem){
            val movePosition = position - firstVisibleItem
            if (movePosition>0 && movePosition< recyclerView.childCount){
                val top = recyclerView.getChildAt(movePosition).top
                recyclerView.smoothScrollBy(0,top)
            }
        }else{
            recyclerView.smoothScrollToPosition(position)
            mToPosition = position
            if (type == 1){
                mShouldScrollContent = true
            }else{
                mShouldScrollTitle = true
            }
        }
    }

    fun backToTop(){
        ry_content_list.smoothScrollToPosition(0)
        ry_guide_list.smoothScrollToPosition(0)
    }
}