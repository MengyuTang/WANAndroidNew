package com.tang.alex.wanandroid.view.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.transition.Slide
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.gson.Gson
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.adapter.HotKeysAdapter
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.presentor.SearchActivityPresentor
import com.tang.alex.wanandroid.view.IView
import kotlinx.android.synthetic.main.activity_search_view.*
import kotlinx.android.synthetic.main.layout_search_header.*

/**
 * 搜索界面
 */
class SearchViewActivity : BaseActivity(),IView {
    private val TAG = "SearchActivity"

    private val mPresentor = SearchActivityPresentor()

    private var hotKeysAdapter: HotKeysAdapter? = null

    private var commonWebsAdapter: HotKeysAdapter? = null

    private var hotKeysData = ArrayList<String>()

    private var commonWebsData = ArrayList<String>()

    private var mPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)
        window.enterTransition = Slide().setDuration(400)
        window.exitTransition = Slide().setDuration(400)
        mPresentor.onAttachView(this)
        initTitle()
        initRecyclerView()
    }

    private fun initTitle() {
        iv_back.setOnClickListener { finish() }
        iv_search.setOnClickListener {
            val key = auto_text.text
            if (!TextUtils.isEmpty(key))
            searchByKey(key.toString())
        }
//        auto_text.addTextChangedListener(object:TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//        })
    }

    private fun searchByKey(key:String) {
        mPresentor.searchArticles(mPage.toString(),key)
    }

    override fun onResume() {
        super.onResume()
        mPresentor.getHotKeys()
        mPresentor.getCommonWebs()
    }

    private fun initRecyclerView() {
        hotKeysAdapter = HotKeysAdapter(this)
        val layoutManager = GridLayoutManager(this,3)
        ry_hot_key.layoutManager = layoutManager
        ry_hot_key.adapter = hotKeysAdapter

        commonWebsAdapter = HotKeysAdapter(this)
        val layoutManager1 = GridLayoutManager(this,2)
        ry_common_webs.layoutManager = layoutManager1
        ry_common_webs.adapter = commonWebsAdapter
    }

    override fun showLoadingDialog() {
        Log.e(TAG,"showLoadingDialog!!!")
    }

    override fun dismissDialog() {
        Log.e(TAG,"dismissDialog!!!")
    }

    override fun showData(data: BaseBean, type: String) {
        if (type == "hotkey"){
            val hotKeys = data.data as ArrayList<Any>
            for (item in hotKeys){
                val map  = item as Map<String,String>
                hotKeysData.add(map["name"]!!)
            }
            hotKeysAdapter!!.setDatas(hotKeysData)
        }else if (type == "commonWebs"){
            val commonWebs = data.data as ArrayList<Any>
            for (item in commonWebs){
                val map  = item as Map<String,String>
                commonWebsData.add(map["name"]!!)
            }
            commonWebsAdapter!!.setDatas(commonWebsData)
        }else if (type == "searchByKey"){
            Log.e(TAG,Gson().toJson(data))
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
