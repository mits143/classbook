package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class CourseSubTopicData(
    @SerializedName("addedByClassOrTeacherAddress")
    val addedByClassOrTeacherAddress: Any,
    @SerializedName("addedByClassOrTeacherContactNo")
    val addedByClassOrTeacherContactNo: Any,
    @SerializedName("addedByClassOrTeacherEmail")
    val addedByClassOrTeacherEmail: Any,
    @SerializedName("addedByClassOrTeacherId")
    val addedByClassOrTeacherId: Int,
    @SerializedName("addedByClassOrTeacherName")
    val addedByClassOrTeacherName: Any,
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
    val category: Any,
    @SerializedName("categoryUrl")
    val categoryUrl: Any,
    @SerializedName("classOrTeacherEstablishmentDate")
    val classOrTeacherEstablishmentDate: Any,
    @SerializedName("courseCount")
    val courseCount: Int,
    @SerializedName("courseId")
    val courseId: Int,
    @SerializedName("courseImageUrl")
    val courseImageUrl: Any,
    @SerializedName("courseName")
    val courseName: String,
    @SerializedName("description")
    val description: Any,
    @SerializedName("favouriteCount")
    val favouriteCount: Int,
    @SerializedName("testimonial")
    val testimonial: TestimonialXX,
    @SerializedName("topicListModel")
    val topicListModel: List<TopicModelXX>
)