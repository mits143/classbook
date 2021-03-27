package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SubjectTopicData: Serializable {
    @SerializedName("active")
    val active: Boolean = false
    @SerializedName("deleted")
    val deleted: Boolean = false
    @SerializedName("description")
    val description: String = ""
    @SerializedName("entityId")
    val entityId: Int = 0
    @SerializedName("entityName")
    val entityName: String = ""
    @SerializedName("topicId")
    val topicId: Int = 0
    @SerializedName("smbId")
    val smbId: Int = 0
    @SerializedName("subjectId")
    val subjectId: Int = 0
    @SerializedName("imageUrl")
    val imageUrl: String = ""
    @SerializedName("name")
    val name: String = ""
}