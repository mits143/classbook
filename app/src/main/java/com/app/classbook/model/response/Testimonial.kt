package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class Testimonial(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("byEntityId")
    val byEntityId: Int,
    @SerializedName("byModuleId")
    val byModuleId: Int,
    @SerializedName("clientName")
    val clientName: String,
    @SerializedName("createdOn")
    val createdOn: String,
    @SerializedName("descrption")
    val descrption: String,
    @SerializedName("entityId")
    val entityId: Int,
    @SerializedName("entityName")
    val entityName: Any,
    @SerializedName("forEntityId")
    val forEntityId: Int,
    @SerializedName("forModuleId")
    val forModuleId: Int,
    @SerializedName("moduleId")
    val moduleId: Int,
    @SerializedName("moduleName")
    val moduleName: String,
    @SerializedName("photoUrl")
    val photoUrl: Any,
    @SerializedName("position")
    val position: Any,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("ratingForAudioQuality")
    val ratingForAudioQuality: Int,
    @SerializedName("ratingForAudioQualityDescription")
    val ratingForAudioQualityDescription: String,
    @SerializedName("ratingForCourses")
    val ratingForCourses: Int,
    @SerializedName("ratingForCoursesDescription")
    val ratingForCoursesDescription: String,
    @SerializedName("ratingForDescriptionOfPoints")
    val ratingForDescriptionOfPoints: Int,
    @SerializedName("ratingForDescriptionOfPointsDescription")
    val ratingForDescriptionOfPointsDescription: String,
    @SerializedName("ratingForLengthOfVideo")
    val ratingForLengthOfVideo: Int,
    @SerializedName("ratingForLengthOfVideoDescription")
    val ratingForLengthOfVideoDescription: String,
    @SerializedName("ratingForSyllabus")
    val ratingForSyllabus: Int,
    @SerializedName("ratingForSyllabusDescription")
    val ratingForSyllabusDescription: String,
    @SerializedName("ratingForVideoQuality")
    val ratingForVideoQuality: Int,
    @SerializedName("ratingForVideoQualityDescription")
    val ratingForVideoQualityDescription: String
)