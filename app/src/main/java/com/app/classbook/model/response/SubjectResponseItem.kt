package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class SubjectResponseItem(
    @SerializedName("id")
    var id: Int, // 2
    @SerializedName("name")
    var name: String, // Hindi
    @SerializedName("inCart")
    var inCart: Boolean, // Hindi
    @SerializedName("subjectMappingId")
    var subjectMappingId: Int // Hindi
)