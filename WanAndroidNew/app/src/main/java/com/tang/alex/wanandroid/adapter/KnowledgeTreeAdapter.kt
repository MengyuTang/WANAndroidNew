package com.tang.alex.wanandroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tang.alex.wanandroid.R
import kotlinx.android.synthetic.main.item_knowlwdge_tree.view.*

class KnowledgeTreeAdapter(private val mContext:Context):RecyclerView.Adapter<KnowledgeTreeAdapter.KnowledgeTreeHolder>() {

    private var tree:List<String>? = null

    fun setDatas(tree:List<String>){
        this.tree = tree
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnowledgeTreeHolder {
        return KnowledgeTreeHolder(LayoutInflater.from(mContext).inflate(R.layout.item_knowlwdge_tree,parent,false))
    }

    override fun getItemCount(): Int {
        return if(null == tree){
            0
        }else{
            tree!!.size
        }
    }

    override fun onBindViewHolder(holder: KnowledgeTreeHolder, position: Int) {
        if (itemCount >0){
            with(holder.itemView){
                tv_name.text = tree!![position]
            }
        }
    }

    inner class KnowledgeTreeHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}