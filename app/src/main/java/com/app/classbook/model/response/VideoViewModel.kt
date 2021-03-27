package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class VideoViewModel(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("subTopicId")
    val subTopicId: Int,
    @SerializedName("subTopicVideoId")
    val subTopicVideoId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)