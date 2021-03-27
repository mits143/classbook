package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class BoardsData(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
)