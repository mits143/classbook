package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class TrainingVideo(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("createdDate")
    val createdDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("trainingVideoId")
    val trainingVideoId: Int,
    @SerializedName("updatedDate")
    val updatedDate: String,
    @SerializedName("videoUrl")
    val videoUrl: String
)