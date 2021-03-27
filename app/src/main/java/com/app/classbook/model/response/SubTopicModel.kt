package com.app.classbook.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SubTopicModel: Serializable{
    @SerializedName("active")
    val active: Boolean = false
    @SerializedName("dateOfActivation")
    val dateOfActivation: String = ""
    @SerializedName("dateOfUpload")
    val dateOfUpload: String = ""
    @SerializedName("deleted")
    val deleted: Boolean = false
    @SerializedName("description")
    val description: String = ""
    @SerializedName("imageUrl")
    val imageUrl: String = ""
    @SerializedName("name")
    val name: String = ""
    @SerializedName("subTopicId")
    val subTopicId: Int = 0
    @SerializedName("topicId")
    val topicId: Int = 0
    @SerializedName("uploadedByUserId")
    val uploadedByUserId: Int = 0
    @SerializedName("videoLink")
    val videoLink: String = ""

}
