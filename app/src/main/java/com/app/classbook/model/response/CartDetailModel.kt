package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class CartDetailModel(
    @SerializedName("actualFees")
    val actualFees: Double,
    @SerializedName("boardName")
    val boardName: String,
    @SerializedName("cartId")
    val cartId: Int,
    @SerializedName("enityName")
    val enityName: String,
    @SerializedName("entityId")
    val entityId: Int,
    @SerializedName("learningType")
    val learningType: String,
    @SerializedName("mappingId")
    val mappingId: Int,
    @SerializedName("mediumName")
    val mediumName: String,
    @SerializedName("moduleId")
    val moduleId: Int,
    @SerializedName("ourFees")
    val ourFees: Double,
    @SerializedName("providerName")
    val providerName: String,
    @SerializedName("providerType")
    val providerType: String,
    @SerializedName("standardsName")
    val standardsName: String,
    @SerializedName("typeOfMapping")
    val typeOfMapping: String
)