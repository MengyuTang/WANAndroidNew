package com.tang.alex.wanandroid.model.bean

import com.google.gson.annotations.SerializedName

data class SearchBean(
        @SerializedName("id") val id: Int, // 6
        @SerializedName("link") val link: String,
        @SerializedName("name") val name: String, // 面试
        @SerializedName("order") val order: Int, // 1
        @SerializedName("visible") val visible: Int // 1
)