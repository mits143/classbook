package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SubjectTopicResponse : Serializable {
    @SerializedName("data")
    val `data`: List<SubjectTopicData> = arrayListOf()

    @SerializedName("message")
    val message: String = ""
}
