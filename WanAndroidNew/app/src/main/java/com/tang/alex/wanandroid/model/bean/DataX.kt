package com.tang.alex.wanandroid.model.bean

import com.google.gson.annotations.SerializedName

data class DataX(
        @SerializedName("apkLink") val apkLink: String,
        @SerializedName("author") val author: String, // samlss
        @SerializedName("chapterId") val chapterId: Int, // 363
        @SerializedName("chapterName") val chapterName: String, // 创意汇
        @SerializedName("collect") val collect: Boolean, // false
        @SerializedName("courseId") val courseId: Int, // 13
        @SerializedName("desc") val desc: String, // 一个可以给view显示粒子爆炸/绽放效果的android库
        @SerializedName("envelopePic") val envelopePic: String, // http://wanandroid.com/blogimgs/f0cfbe76-2e96-4df3-b83d-933c40546c63.png
        @SerializedName("fresh") val fresh: Boolean, // false
        @SerializedName("id") val id: Int, // 7737
        @SerializedName("link") val link: String, // http://www.wanandroid.com/blog/show/2478
        @SerializedName("niceDate") val niceDate: String, // 2019-01-03
        @SerializedName("origin") val origin: String,
        @SerializedName("projectLink") val projectLink: String, // https://github.com/samlss/Bloom
        @SerializedName("publishTime") val publishTime: Long, // 1546517257000
        @SerializedName("superChapterId") val superChapterId: Int, // 294
        @SerializedName("superChapterName") val superChapterName: String, // 开源项目主Tab
        @SerializedName("tags") val tags: List<Tag>,
        @SerializedName("title") val title: String, // 一个可以给view显示粒子爆炸/绽放效果的android库 Bloom
        @SerializedName("type") val type: Int, // 0
        @SerializedName("userId") val userId: Int, // -1
        @SerializedName("visible") val visible: Int, // 1
        @SerializedName("zan") val zan: Int // 0
)