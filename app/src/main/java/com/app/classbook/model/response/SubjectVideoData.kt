package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class SubjectVideoData(
    @SerializedName("description")
    val description: String,
    @SerializedName("subtopicId")
    val subtopicId: Int,
    @SerializedName("videoId")
    val videoId: Int,
    @SerializedName("subjectId")
    val subjectId: Int,
    @SerializedName("topicId")
    val topicId: Int,
    @SerializedName("smbId")
    val smbId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("videoUrl")
    val videoUrl: String
)