package com.tang.alex.wanandroid.model.bean

import com.google.gson.annotations.SerializedName

data class KnowledgeTreeBean(
        @SerializedName("children") val children: List<Children>,
        @SerializedName("courseId") val courseId: Int, // 13
        @SerializedName("id") val id: Int, // 150
        @SerializedName("name") val name: String, // 开发环境
        @SerializedName("order") val order: Int, // 1
        @SerializedName("parentChapterId") val parentChapterId: Int, // 0
        @SerializedName("userControlSetTop") val userControlSetTop: Boolean, // false
        @SerializedName("visible") val visible: Int // 1
)