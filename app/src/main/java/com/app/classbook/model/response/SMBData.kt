package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class
SMBData: Serializable{
    @SerializedName("boardId")
    val boardId: Int = 0
    @SerializedName("boardName")
    val boardName: String = ""
    @SerializedName("mediumId")
    val mediumId: Int = 0
    @SerializedName("mediumName")
    val mediumName: String = ""
    @SerializedName("smbId")
    val smbId: Int = 0
    @SerializedName("standardId")
    val standardId: Int = 0
    @SerializedName("standardName")
    val standardName: String = ""

}
