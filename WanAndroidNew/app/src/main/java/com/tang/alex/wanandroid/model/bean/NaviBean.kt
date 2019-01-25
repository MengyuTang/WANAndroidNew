package com.tang.alex.wanandroid.model.bean

import com.google.gson.annotations.SerializedName

data class NaviBean(
        @SerializedName("articles") val articles: List<ArticleBean>,
        @SerializedName("cid") val cid: String, // 369
        @SerializedName("name") val name: String // 短视频SDK
)