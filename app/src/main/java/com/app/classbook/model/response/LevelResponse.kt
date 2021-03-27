package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class LevelResponse(
    @SerializedName("data")
    val `data`: LevelData,
    @SerializedName("message")
    val message: String
)