package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class TopicModelX(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("deleted")
    val deleted: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("entityId")
    val entityId: Int,
    @SerializedName("entityName")
    val entityName: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("isCourse")
    val isCourse: Boolean,
    @SerializedName("isSubject")
    val isSubject: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("smbId")
    val smbId: Int,
    @SerializedName("subTopicListModel")
    val subTopicListModel: List<Any>,
    @SerializedName("subjectId")
    val subjectId: Int,
    @SerializedName("subjectName")
    val subjectName: Any,
    @SerializedName("topicId")
    val topicId: Int
)