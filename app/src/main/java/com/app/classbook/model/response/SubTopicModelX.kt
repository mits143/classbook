package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class SubTopicModelX(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("dateOfActivation")
    val dateOfActivation: String,
    @SerializedName("dateOfUpload")
    val dateOfUpload: String,
    @SerializedName("deleted")
    val deleted: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("subTopicId")
    val subTopicId: Int,
    @SerializedName("topicId")
    val topicId: Int,
    @SerializedName("uploadedByUserId")
    val uploadedByUserId: Int,
    @SerializedName("videoLink")
    val videoLink: String
)