package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class BoardsResponse(
    @SerializedName("data")
    val `data`: List<BoardsData>,
    @SerializedName("message")
    val message: String
)