package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TopicModelData : Serializable {
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

    @SerializedName("imageUrl")
    val imageUrl: String = ""

    @SerializedName("isCourse")
    val isCourse: Boolean = false

    @SerializedName("isSubject")
    val isSubject: Boolean = false

    @SerializedName("name")
    val name: String = ""

    @SerializedName("smbId")
    val smbId: Int = 0

    @SerializedName("subTopicListModel")
    val subTopicListModel: List<Any> = arrayListOf()

    @SerializedName("subjectId")
    val subjectId: Int = 0

    @SerializedName("subjectName")
    val subjectName: String = ""

    @SerializedName("topicId")
    val topicId: Int = 0
}
