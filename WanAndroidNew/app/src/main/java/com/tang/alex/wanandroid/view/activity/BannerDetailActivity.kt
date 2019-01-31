package com.tang.alex.wanandroid.view.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.utils.MyWebClient
import kotlinx.android.synthetic.main.activity_banner_detail.*

class BannerDetailActivity : AppCompatActivity() {

    private var currentActivity:BannerDetailActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_detail)
        val localLayoutParams = window.attributes
        localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
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

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        currentActivity = this
        bannerDetailView.webViewClient = MyWebClient()
        bannerDetailView.loadUrl(intent.getStringExtra("url"))
        bannerDetailView.settings.setSupportZoom(true)
        bannerDetailView.settings.javaScriptEnabled = true
        bannerDetailView.webChromeClient = object:WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if(newProgress==100){
                    progressBar.visibility = View.GONE//加载完网页进度条消失
                }
                else{
                    progressBar.visibility = View.VISIBLE//开始加载网页时显示进度条
                    progressBar.progress = newProgress//设置进度值
                }
            }
        }
    }

    override fun onBackPressed() {
        if (bannerDetailView.canGoBack()){
            bannerDetailView.goBack()
        }else{
            super.onBackPressed()
        }
    }
}
