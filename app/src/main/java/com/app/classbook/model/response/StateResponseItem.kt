package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class StateResponseItem(
    @SerializedName("active")
    var active: Boolean, // true
    @SerializedName("id")
    var id: Int, // 4
    @SerializedName("name")
    var name: String, // UP
    var isChecked: Boolean
)