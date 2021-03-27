package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SubTopicData : Serializable {
    @SerializedName("active")
    val active: Boolean = false

    @SerializedName("description")
    val description: String = ""

    @SerializedName("imageUrl")
    val imageUrl: String = ""

    @SerializedName("name")
    val name: String = ""

    @SerializedName("subtopicId")
    val subTopicId: Int = 0

    @SerializedName("smbId")
    val smbId: Int = 0

    @SerializedName("subjectId")
    val subjectId: Int = 0

    @SerializedName("topicId")
    val topicId: Int = 0

    @SerializedName("uploadedByUserId")
    val uploadedByUserId: Int = 0

    @SerializedName("videoLink")
    val videoLink: String = ""
}