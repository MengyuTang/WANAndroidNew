package com.tang.alex.wanandroid.model.bean

import com.google.gson.annotations.SerializedName

data class BaseBean(
        @SerializedName("data") val data: Any,
        @SerializedName("errorCode") val errorCode: Int, // 0
        @SerializedName("errorMsg") val errorMsg: String
)