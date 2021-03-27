package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class InstructorModel(
    @SerializedName("instructorDescription")
    val instructorDescription: String,
    @SerializedName("instructorImageUrl")
    val instructorImageUrl: String,
    @SerializedName("instructorName")
    val instructorName: String
)