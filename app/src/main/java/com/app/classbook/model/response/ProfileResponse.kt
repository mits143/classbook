package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class ProfileResponse(

    @SerializedName("data")
    val `data`: ProfileData,
    @SerializedName("message")
    val message: String
)