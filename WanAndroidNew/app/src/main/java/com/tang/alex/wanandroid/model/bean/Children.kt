package com.tang.alex.wanandroid.model.bean

import com.google.gson.annotations.SerializedName

data class Children(
        @SerializedName("children") val children: List<Any>,
        @SerializedName("courseId") val courseId: Int, // 13
        @SerializedName("id") val id: Int, // 269
        @SerializedName("name") val name: String, // 官方发布
        @SerializedName("order") val order: Int, // 1002
        @SerializedName("parentChapterId") val parentChapterId: Int, // 150
        @SerializedName("userControlSetTop") val userControlSetTop: Boolean, // false
        @SerializedName("visible") val visible: Int // 1
)