package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class ClassDetailResponse(
    @SerializedName("data")
    val `data`: ClassDetailData,
    @SerializedName("message")
    val message: String
)