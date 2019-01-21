package com.tang.alex.wanandroid.model.bean

import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("curPage") val curPage: Int, // 1
        @SerializedName("datas") val datas: List<DataX>,
        @SerializedName("offset") val offset: Int, // 0
        @SerializedName("over") val over: Boolean, // false
        @SerializedName("pageCount") val pageCount: Int, // 19
        @SerializedName("size") val size: Int, // 15
        @SerializedName("total") val total: Int // 274
)