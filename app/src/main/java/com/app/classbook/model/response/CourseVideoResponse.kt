package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class CourseVideoResponse(
    @SerializedName("data")
    val `data`: CourseVideoData,
    @SerializedName("message")
    val message: String
)