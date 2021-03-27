package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class CourseVideoData(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("className")
    val className: Any,
    @SerializedName("courseId")
    val courseId: Int,
    @SerializedName("courseName")
    val courseName: String,
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
    @SerializedName("smbId")
    val smbId: Int,
    @SerializedName("subTopicId")
    val subTopicId: Int,
    @SerializedName("subjectId")
    val subjectId: Int,
    @SerializedName("subjectName")
    val subjectName: Any,
    @SerializedName("topicId")
    val topicId: Int,
    @SerializedName("topicName")
    val topicName: String,
    @SerializedName("uploadedByUserId")
    val uploadedByUserId: Int,
    @SerializedName("videoLink")
    val videoLink: String,
    @SerializedName("videoViewModelList")
    val videoViewModelList: List<CourseVideoListdata>
)