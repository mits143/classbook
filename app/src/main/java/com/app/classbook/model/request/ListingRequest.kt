package com.app.classbook.model.request


import com.google.gson.annotations.SerializedName

data class ListingRequest(
    @SerializedName("boardId")
    val boardId: Int,
    @SerializedName("cityId")
    val cityId: Int,
    @SerializedName("classId")
    val classId: Int,
    @SerializedName("courseCategoryId")
    val courseCategoryId: Int,
    @SerializedName("expertiesId")
    val expertiesId: Int,
    @SerializedName("mediumId")
    val mediumId: Int,
    @SerializedName("onlineLive")
    val onlineLive: Boolean,
    @SerializedName("onlineLivePhysical")
    val onlineLivePhysical: Boolean,
    @SerializedName("pageIndex")
    val pageIndex: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("searchName")
    val searchName: String,
    @SerializedName("standardId")
    val standardId: Int,
    @SerializedName("stateId")
    val stateId: Int,
    @SerializedName("teacherId")
    val teacherId: Int
)