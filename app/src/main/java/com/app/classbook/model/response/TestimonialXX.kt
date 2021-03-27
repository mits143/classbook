package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class TestimonialXX(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("byEntityId")
    val byEntityId: Int,
    @SerializedName("byModuleId")
    val byModuleId: Int,
    @SerializedName("clientName")
    val clientName: Any,
    @SerializedName("createdOn")
    val createdOn: Any,
    @SerializedName("descrption")
    val descrption: Any,
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
    val moduleName: Any,
    @SerializedName("photoUrl")
    val photoUrl: Any,
    @SerializedName("position")
    val position: Any,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("ratingForAudioQuality")
    val ratingForAudioQuality: Int,
    @SerializedName("ratingForAudioQualityDescription")
    val ratingForAudioQualityDescription: Any,
    @SerializedName("ratingForCourses")
    val ratingForCourses: Int,
    @SerializedName("ratingForCoursesDescription")
    val ratingForCoursesDescription: Any,
    @SerializedName("ratingForDescriptionOfPoints")
    val ratingForDescriptionOfPoints: Int,
    @SerializedName("ratingForDescriptionOfPointsDescription")
    val ratingForDescriptionOfPointsDescription: Any,
    @SerializedName("ratingForLengthOfVideo")
    val ratingForLengthOfVideo: Int,
    @SerializedName("ratingForLengthOfVideoDescription")
    val ratingForLengthOfVideoDescription: Any,
    @SerializedName("ratingForSyllabus")
    val ratingForSyllabus: Int,
    @SerializedName("ratingForSyllabusDescription")
    val ratingForSyllabusDescription: Any,
    @SerializedName("ratingForVideoQuality")
    val ratingForVideoQuality: Int,
    @SerializedName("ratingForVideoQualityDescription")
    val ratingForVideoQualityDescription: Any
)