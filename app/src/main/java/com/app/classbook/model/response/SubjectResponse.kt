package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SubjectResponse: Serializable {
    @SerializedName("data")
    val `data`: SubjectResponseData? = null
    @SerializedName("message")
    val message: String = ""
}
