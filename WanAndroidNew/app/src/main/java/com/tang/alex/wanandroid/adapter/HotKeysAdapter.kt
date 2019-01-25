package com.tang.alex.wanandroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tang.alex.wanandroid.R
import kotlinx.android.synthetic.main.item_hot_key.view.*

open class HotKeysAdapter(private val mContext: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var datas:ArrayList<String>? = null

    public fun setDatas(datas:ArrayList<String>?){
        this.datas = datas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HotKeyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_hot_key,parent,false))
    }

    override fun getItemCount(): Int {
        return if (null == datas){
            0
        }else{
            datas!!.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hotKeyHolder = holder as HotKeyHolder
        if (itemCount>0){
            hotKeyHolder.itemView.tv_key.text = datas!![position]
        }
    }

    class HotKeyHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}