package com.tang.alex.wanandroid.model.bean

import com.google.gson.annotations.SerializedName

data class CommonWebSiteBean(
        @SerializedName("icon") val icon: String,
        @SerializedName("id") val id: Int, // 30
        @SerializedName("link") val link: String, // http://www.wanandroid.com/blog/show/2090
        @SerializedName("name") val name: String, // 优质资源领取
        @SerializedName("order") val order: Int, // 8888
        @SerializedName("visible") val visible: Int // 1
)