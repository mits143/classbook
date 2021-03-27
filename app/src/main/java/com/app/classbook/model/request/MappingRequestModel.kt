package com.app.classbook.model.request


import com.google.gson.annotations.SerializedName

data class MappingRequestModel(
    @SerializedName("CourseCategoryIds")
    var courseCategoryIds: String, // 1,3,4
    @SerializedName("StandardMediumBoard")
    var standardMediumBoard: List<StandardMediumBoard>,
    @SerializedName("SubjectSpecialityIds")
    var subjectSpecialityIds: String // 1,2
)