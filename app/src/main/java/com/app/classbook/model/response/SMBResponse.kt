package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class SMBResponse(
    @SerializedName("data")
    val `data`: List<SMBData>,
    @SerializedName("message")
    val message: String
)