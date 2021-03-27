package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class ClassDetailData(
    @SerializedName("address")
    val address: String,
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
    @SerializedName("className")
    val className: String,
    @SerializedName("teacherName")
    val teacherName: String,
    @SerializedName("contactNo")
    val contactNo: String,
    @SerializedName("courseCount")
    val courseCount: Int,
    @SerializedName("curriculum")
    val curriculum: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("establishmentDate")
    val establishmentDate: Any,
    @SerializedName("favouriteCount")
    val favouriteCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("instructorListModel")
    val instructorListModel: List<InstructorModel>,
    @SerializedName("introductionVideoUrl")
    val introductionVideoUrl: String,
    @SerializedName("overviewContent")
    val overviewContent: String,
    @SerializedName("overviewTitle")
    val overviewTitle: String,
    @SerializedName("profilePhoto")
    val profilePhoto: String,
    @SerializedName("standardMediumBoardMapping")
    val standardMediumBoardMapping: List<StandardMediumBoardMapping>,
    @SerializedName("testimonial")
    val testimonial: Testimonial,
    @SerializedName("totalRating")
    val totalRating: Int,
    @SerializedName("website")
    val website: String
)