package com.tang.alex.wanandroid.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.model.bean.ArticleBean
import com.tang.alex.wanandroid.utils.GlideImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.item_article_info.view.*
import kotlinx.android.synthetic.main.item_banners.view.*
import java.math.BigDecimal

class HomeAdapter(private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var articles: List<ArticleBean>? = null
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)
    private var images: List<String>? = null
    private var titles: List<String>? = null
    private var imageLink: List<String>? = null
    private val ITEM_TYPE_BANNER = 1001
    private val ITEM_TYPE_ARTICLE = 1002

    fun setDatas(images: List<String>,titles: List<String>,imageLink: List<String>,articles: List<ArticleBean>) {
        this.images = images
        this.titles = titles
        this.imageLink = imageLink
        this.articles = articles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return if (i == ITEM_TYPE_BANNER){
            BannerHolder(mLayoutInflater.inflate(R.layout.item_banners, viewGroup, false))
        }else{
            ArticleHolder(mLayoutInflater.inflate(R.layout.item_article_info, viewGroup, false))
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is BannerHolder){
            if (null != images && images!!.isNotEmpty()){
                with(viewHolder.itemView){
                    //设置图片加载器
                    banner.setImageLoader(GlideImageLoader())
                    banner.setImages(images)
                    //设置banner样式
                    banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                    //设置banner动画效果
                    banner.setBannerAnimation(Transformer.DepthPage)
                    //设置标题集合（当banner样式有显示title时）
                    banner.setBannerTitles(titles)
                    //设置自动轮播，默认为true
                    banner.isAutoPlay(true)
                    //设置轮播时间
                    banner.setDelayTime(1500)
                    //设置指示器位置（当banner模式中有指示器时）
                    banner.setIndicatorGravity(BannerConfig.CENTER)
                    //banner设置方法全部调用完毕时最后调用
                    banner.start()
                    banner.setOnClickListener {
                        //TODO 根据imageLink加载相应页面
                    }
                }
            }
        }else if(viewHolder is ArticleHolder){
            if (articles!!.isNotEmpty()) {
                with(viewHolder.itemView) {
                    val article = articles!![position-1] as Map<String, Any>
                    tv_label.text = article["chapterName"].toString()
                    tv_author.text = article["author"].toString()
                    tv_title.text = article["title"].toString()
                    val time = BigDecimal(article["publishTime"].toString()).toLong()
                    val dateTime = System.currentTimeMillis().minus(time.toString().toLong())
                    with(dateTime) {
                        if (this < 2 * 24 * 60 * 60 * 1000) {
                            when {
                                this < 60 * 60 * 1000 -> {
                                    tv_new.visibility = View.VISIBLE
                                    tv_time.text = this.div(60 * 1000).toString() + "分钟前"
                                }
                                this < 24 * 60 * 60 * 1000 -> {
                                    tv_new.visibility = View.VISIBLE
                                    tv_time.text = this.div(60 * 60 * 1000).toString() + "小时前"
                                }
                                else -> {
                                    tv_new.visibility = View.GONE
                                    tv_time.text = this.div(24 * 60 * 60 * 1000).toString() + "天前"
                                }
                            }
                        } else {
                            tv_new.visibility = View.GONE
                            tv_time.text = article["niceDate"].toString()
                        }
                    }
                    if (article["envelopePic"].toString().isEmpty()){
                        picture.visibility = View.GONE
                    }else{
                        picture.visibility = View.VISIBLE
                        Glide.with(mContext)
                                .load(article["envelopePic"].toString())
                                .into(picture)
                    }
                    iv_collect.setOnClickListener {
                        iv_collect.isSelected = !iv_collect.isSelected
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0){
            ITEM_TYPE_BANNER
        }else{
            ITEM_TYPE_ARTICLE
        }
    }

    override fun getItemCount(): Int {
        return if (null == articles) {
            if (null == images || images!!.isEmpty()){
                0
            }else{
                1
            }
        } else articles!!.size+1
    }

    inner class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
