package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class FavoriteResponseItem(
    @SerializedName("name")
    var name: String,
    @SerializedName("entityName")
    var entityName: String
)