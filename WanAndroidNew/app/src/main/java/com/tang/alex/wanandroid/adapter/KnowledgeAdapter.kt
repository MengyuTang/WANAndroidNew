package com.tang.alex.wanandroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.model.bean.KnowledgeInfo
import com.tang.alex.wanandroid.model.bean.KnowledgeTreeBean
import kotlinx.android.synthetic.main.item_knowledge.view.*
import java.math.BigDecimal

class KnowledgeAdapter(mContext:Context):RecyclerView.Adapter<KnowledgeAdapter.KnowledgeHolder>() {

    private var titlesMap = HashMap<String,List<KnowledgeInfo>>()

    private var titles = ArrayList<String>()

    private var mLayoutInflater:LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnowledgeHolder {
        val view:View =  mLayoutInflater.inflate(R.layout.item_knowledge,parent,false)
        return KnowledgeHolder(view)
    }

    override fun getItemCount(): Int {
        return titlesMap.size
    }

    override fun onBindViewHolder(holder: KnowledgeHolder, position: Int) {
        if (titlesMap.size>0){
            with(holder.itemView){
                tv_know_title.text = titles[position]
                val list = titlesMap[titles[position]]!!
                val listName = ArrayList<String>()
                val articleMap = HashMap<String,Int>()
                val sbf = StringBuffer()
                for (info in list){
                    sbf.append(info.name+"    ")
                    listName.add(info.name)
                    articleMap[info.name] = BigDecimal(info.id).toInt()
                }
                tv_know_content.text = sbf.toString()
                this.setOnClickListener {
//                    val intent = Intent()
//                    intent.setClass(context, KnowledgeDetailActivity::class.java)
//                    intent.putExtra("title", titles[position])
//                    intent.putExtra("name",listName)
//                    intent.putExtra("articleMap",articleMap)
//                    context.startActivity(intent)
                }
            }
        }
    }

    fun setDatas(titles: ArrayList<String>,titlesMap: HashMap<String,List<KnowledgeInfo>>){
        this.titles = titles
        this.titlesMap = titlesMap
        notifyDataSetChanged()
    }

    class KnowledgeHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}