package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class CourseDetailData(
    @SerializedName("addedByClassOrTeacherAddress")
    val addedByClassOrTeacherAddress: String,
    @SerializedName("addedByClassOrTeacherContactNo")
    val addedByClassOrTeacherContactNo: String,
    @SerializedName("addedByClassOrTeacherEmail")
    val addedByClassOrTeacherEmail: String,
    @SerializedName("addedByClassOrTeacherId")
    val addedByClassOrTeacherId: Int,
    @SerializedName("addedByClassOrTeacherName")
    val addedByClassOrTeacherName: String,
    @SerializedName("addedByClassOrTeacherWebsite")
    val addedByClassOrTeacherWebsite: Any,
    @SerializedName("averageRating")
    val averageRating: Int,
    @SerializedName("averageRatingForAudioQuality")
    val averageRatingForAudioQuality: Int,
    @SerializedName("averageRatingForCourses")
    val averageRatingForCourses: Int,
    @SerializedName("averageRatingForDescriptionOfPoints")
    val averageRatingForDescriptionOfPoints: Int,
    @SerializedName("averageRatingForLengthOfVideo")
    val averageRatingForLengthOfVideo: Int,
    @SerializedName("averageRatingForSyllabus")
    val averageRatingForSyllabus: Int,
    @SerializedName("averageRatingForVideoQuality")
    val averageRatingForVideoQuality: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("categoryUrl")
    val categoryUrl: String,
    @SerializedName("classOrTeacherEstablishmentDate")
    val classOrTeacherEstablishmentDate: String,
    @SerializedName("courseCount")
    val courseCount: Int,
    @SerializedName("courseId")
    val courseId: Int,
    @SerializedName("courseImageUrl")
    val courseImageUrl: String,
    @SerializedName("courseName")
    val courseName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("favouriteCount")
    val favouriteCount: Int,
    @SerializedName("testimonial")
    val testimonial: TestimonialX,
    @SerializedName("topicListModel")
    val topicListModel: List<TopicModelX>
)