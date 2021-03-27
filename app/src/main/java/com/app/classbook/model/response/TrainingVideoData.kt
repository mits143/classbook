package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class TrainingVideoData(
    @SerializedName("trainingVideoList")
    val trainingVideoList: List<TrainingVideo>
)