package com.tang.alex.wanandroid.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.model.bean.ArticleBean
import com.tang.alex.wanandroid.view.activity.BannerDetailActivity
import kotlinx.android.synthetic.main.item_article_info.view.*
import java.math.BigDecimal

class KnowledgeDetailAdapter(private val mContext: Context) : RecyclerView.Adapter<KnowledgeDetailAdapter.ArticleHolder>() {
    private var articles: ArrayList<ArticleBean>? = null
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    fun setDatas(articles: ArrayList<ArticleBean>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ArticleHolder {
           return ArticleHolder(mLayoutInflater.inflate(R.layout.item_knowledge_article_info, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ArticleHolder, position: Int) {
            if (articles!!.isNotEmpty()) {
                with(viewHolder.itemView) {
                    val article = articles!![position] as Map<String, Any>
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
                    iv_collect.setOnClickListener {
                        iv_collect.isSelected = !iv_collect.isSelected
                    }

                    this.setOnClickListener {
                        val intent = Intent()
                        intent.setClass(mContext, BannerDetailActivity::class.java)
                        intent.putExtra("url", article["link"].toString())
                        intent.putExtra("title",article["title"].toString())
                        mContext.startActivity(intent)
                    }
                }
            }
    }

    override fun getItemCount(): Int {
        return if (null == articles) {
            0
        } else articles!!.size
    }

    inner class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
