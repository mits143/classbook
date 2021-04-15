package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SMBResponse: Serializable {
    @SerializedName("data")
    val `data`: List<SMBData> = arrayListOf()

    @SerializedName("message")
    val message: String = ""
}