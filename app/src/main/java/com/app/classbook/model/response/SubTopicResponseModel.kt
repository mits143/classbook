package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class SubTopicResponseModel(
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("imageUrl")
    var imageUrl: String,
    @SerializedName("videoLink")
    var videoLink: String
)