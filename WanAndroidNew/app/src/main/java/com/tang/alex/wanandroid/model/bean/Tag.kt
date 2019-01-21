package com.tang.alex.wanandroid.model.bean

import com.google.gson.annotations.SerializedName

data class Tag(
        @SerializedName("name") val name: String, // 项目
        @SerializedName("url") val url: String // /project/list/1?cid=363
)