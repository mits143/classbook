package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class TrainingVideoResponse(
    @SerializedName("data")
    val `data`: TrainingVideoData,
    @SerializedName("message")
    val message: String
)