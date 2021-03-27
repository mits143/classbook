package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class CourseDetailResponse(
    @SerializedName("data")
    val `data`: CourseDetailData,
    @SerializedName("message")
    val message: String
)