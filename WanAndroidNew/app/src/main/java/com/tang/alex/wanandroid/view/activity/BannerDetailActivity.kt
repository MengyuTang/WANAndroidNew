package com.tang.alex.wanandroid.view.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.utils.MyWebClient
import kotlinx.android.synthetic.main.activity_banner_detail.*

class BannerDetailActivity : AppCompatActivity() {

    private var currentActivity:BannerDetailActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_detail)
        initView()
        initToolbar()
    }

    private fun initToolbar(){
        banner_toolBar.title = intent.getStringExtra("title")
        banner_toolBar.setTitleTextColor(Color.WHITE)
        banner_toolBar.navigationIcon = resources.getDrawable(R.mipmap.back)
        setSupportActionBar(banner_toolBar)
        banner_toolBar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initView() {
        currentActivity = this
        bannerDetailView.webViewClient = MyWebClient()
        bannerDetailView.loadUrl(intent.getStringExtra("url"))
        bannerDetailView.settings.setSupportZoom(true)
    }
}
