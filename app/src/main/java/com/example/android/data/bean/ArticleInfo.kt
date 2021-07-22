package com.example.android.data.bean

import com.google.gson.annotations.SerializedName

data class DatasItem(@SerializedName("shareDate")
                     val shareDate: Long = 0,
                     @SerializedName("projectLink")
                     val projectLink: String = "",
                     @SerializedName("prefix")
                     val prefix: String = "",
                     @SerializedName("canEdit")
                     val canEdit: Boolean = false,
                     @SerializedName("origin")
                     val origin: String = "",
                     @SerializedName("link")
                     val link: String = "",
                     @SerializedName("title")
                     val title: String = "",
                     @SerializedName("type")
                     val type: Int = 0,
                     @SerializedName("selfVisible")
                     val selfVisible: Int = 0,
                     @SerializedName("apkLink")
                     val apkLink: String = "",
                     @SerializedName("envelopePic")
                     val envelopePic: String = "",
                     @SerializedName("audit")
                     val audit: Int = 0,
                     @SerializedName("chapterId")
                     val chapterId: Int = 0,
                     @SerializedName("host")
                     val host: String = "",
                     @SerializedName("realSuperChapterId")
                     val realSuperChapterId: Int = 0,
                     @SerializedName("id")
                     val id: Int = 0,
                     @SerializedName("courseId")
                     val courseId: Int = 0,
                     @SerializedName("superChapterName")
                     val superChapterName: String = "",
                     @SerializedName("descMd")
                     val descMd: String = "",
                     @SerializedName("publishTime")
                     val publishTime: Long = 0,
                     @SerializedName("niceShareDate")
                     val niceShareDate: String = "",
                     @SerializedName("visible")
                     val visible: Int = 0,
                     @SerializedName("niceDate")
                     val niceDate: String = "",
                     @SerializedName("author")
                     val author: String = "",
                     @SerializedName("zan")
                     val zan: Int = 0,
                     @SerializedName("chapterName")
                     val chapterName: String = "",
                     @SerializedName("userId")
                     val userId: Int = 0,
                     @SerializedName("tags")
                     val tags: List<TagsItem>?,
                     @SerializedName("superChapterId")
                     val superChapterId: Int = 0,
                     @SerializedName("fresh")
                     val fresh: Boolean = false,
                     @SerializedName("collect")
                     val collect: Boolean = false,
                     @SerializedName("shareUser")
                     val shareUser: String = "",
                     @SerializedName("desc")
                     val desc: String = "")

data class TagsItem(@SerializedName("name")
                    val name: String = "",
                    @SerializedName("url")
                    val url: String = "")

data class ArticleData(@SerializedName("over")
                       val over: Boolean = false,
                       @SerializedName("pageCount")
                       val pageCount: Int = 0,
                       @SerializedName("total")
                       val total: Int = 0,
                       @SerializedName("curPage")
                       val curPage: Int = 0,
                       @SerializedName("offset")
                       val offset: Int = 0,
                       @SerializedName("size")
                       val size: Int = 0,
                       @SerializedName("datas")
                       val datas: List<DatasItem>?)


