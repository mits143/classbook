package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class SubjectVideoResponse(
    @SerializedName("data")
    val `data`: List<SubjectVideoData> = arrayListOf(),
    @SerializedName("message")
    val message: String
)