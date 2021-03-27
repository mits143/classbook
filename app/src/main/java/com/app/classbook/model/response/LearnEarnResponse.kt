package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class LearnEarnResponse(
    @SerializedName("data")
    val `data`: List<LearnEarnData>,
    @SerializedName("message")
    val message: String
)