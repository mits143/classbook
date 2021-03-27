package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Subject : Serializable {
    @SerializedName("active")
    val active: Boolean = false
    @SerializedName("distanceFees")
    val distanceFees: Int = 0
    @SerializedName("live")
    val live: Boolean = false
    @SerializedName("online")
    val online: Boolean = false
    @SerializedName("otgsDiscount")
    val otgsDiscount: Int = 0
    @SerializedName("otgsDiscountedFees")
    val otgsDiscountedFees: Int = 0
    @SerializedName("otgsFees")
    val otgsFees: Int = 0
    @SerializedName("physical")
    val physical: Boolean = false
    @SerializedName("physicalFees")
    val physicalFees: Int = 0
    @SerializedName("smbId")
    val smbId: Int = 0
    @SerializedName("subjectId")
    val subjectId: Int = 0
    @SerializedName("subjectMappingId")
    val subjectMappingId: Int = 0
    @SerializedName("subjectName")
    val subjectName: String = ""
}
