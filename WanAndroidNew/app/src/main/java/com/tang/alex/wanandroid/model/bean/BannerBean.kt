package com.tang.alex.wanandroid.model.bean

import com.google.gson.annotations.SerializedName

data class BannerBean(
        @SerializedName("desc") val desc: String, // 一起来做个App吧
        @SerializedName("id") val id: Int, // 10
        @SerializedName("imagePath") val imagePath: String, // http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png
        @SerializedName("isVisible") val isVisible: Int, // 1
        @SerializedName("order") val order: Int, // 3
        @SerializedName("title") val title: String, // 一起来做个App吧
        @SerializedName("type") val type: Int, // 0
        @SerializedName("url") val url: String // http://www.wanandroid.com/blog/show/2
)