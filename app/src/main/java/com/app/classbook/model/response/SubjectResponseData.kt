package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SubjectResponseData : Serializable {
    @SerializedName("modeuleName")
    val modeuleName: String = ""

    @SerializedName("smbId")
    val smbId: Int = 0

    @SerializedName("subjectList")
    val subjectList: List<Subject> = arrayListOf()

    @SerializedName("topicListModel")
    val topicListModel: List<TopicModelData> = arrayListOf()
}