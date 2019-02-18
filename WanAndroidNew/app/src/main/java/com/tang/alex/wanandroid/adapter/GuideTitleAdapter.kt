package com.tang.alex.wanandroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.model.bean.NaviBean
import kotlinx.android.synthetic.main.item_guide_title.view.*
import java.util.*

class GuideTitleAdapter(mContext: Context): RecyclerView.Adapter<GuideTitleAdapter.TitleViewHolder>() {

    private var guideTitles =  ArrayList<NaviBean>()

    private var mLayoutInflater:LayoutInflater = LayoutInflater.from(mContext)

    interface MyClickListener{
        fun onClick(position: Int)
    }

    private var listener:MyClickListener? = null

    fun setMyClickListener(listener:MyClickListener){
        this.listener = listener
    }

    fun setDatas(guideTitles:ArrayList<NaviBean>){
        this.guideTitles = guideTitles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        var view = mLayoutInflater.inflate(R.layout.item_guide_title,parent,false)
        return TitleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return guideTitles.size
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        if (guideTitles.size>0){
            with(holder.itemView){
                this.tv_guide.text = guideTitles[position].name
                this.tv_guide.setTextColor(resources.getColor(R.color.deep_gray))
                this.tv_guide.setOnClickListener {
                    listener?.onClick(position)
                    this.tv_guide.setTextColor(resources.getColor(R.color.colorAccent))
                }
            }
        }
    }

    class TitleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}