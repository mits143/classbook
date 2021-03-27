package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class StandardMediumBoardMapping(
    @SerializedName("boardId")
    val boardId: Int,
    @SerializedName("boardName")
    val boardName: String,
    @SerializedName("mediumId")
    val mediumId: Int,
    @SerializedName("mediumName")
    val mediumName: String,
    @SerializedName("smbId")
    val smbId: Int,
    @SerializedName("standardId")
    val standardId: Int,
    @SerializedName("standardName")
    val standardName: String
)