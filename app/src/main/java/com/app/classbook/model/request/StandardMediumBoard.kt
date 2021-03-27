package com.app.classbook.model.request


import com.google.gson.annotations.SerializedName

data class StandardMediumBoard(
    @SerializedName("BoardId")
    var boardId: Int, // 1
    @SerializedName("MediumId")
    var mediumId: Int, // 1
    @SerializedName("StandardId")
    var standardId: Int, // 1
    @SerializedName("SubjectIds")
    var subjectIds: List<Int>
)