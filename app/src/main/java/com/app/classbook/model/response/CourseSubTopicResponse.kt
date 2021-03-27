package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class CourseSubTopicResponse(
    @SerializedName("data")
    val `data`: CourseSubTopicData,
    @SerializedName("message")
    val message: String
)