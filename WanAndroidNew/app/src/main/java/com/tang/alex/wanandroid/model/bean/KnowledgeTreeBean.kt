package com.tang.alex.wanandroid.model.bean

import com.google.gson.annotations.SerializedName

data class KnowledgeTreeBean(
        @SerializedName("id") val id: Int, // 150
        @SerializedName("name") val name: String // 开发环境
)