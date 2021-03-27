package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class LearnEarnData(
    @SerializedName("description")
    val description: String,
    @SerializedName("howToEarnId")
    val howToEarnId: Int,
    @SerializedName("registrationProcessId")
    val registrationProcessId: Int,
    @SerializedName("title")
    val title: String
)