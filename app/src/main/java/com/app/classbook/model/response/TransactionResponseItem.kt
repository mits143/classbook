package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class TransactionResponseItem(
    @SerializedName("enityName")
    val enityName: String,
    @SerializedName("expireDate")
    val expireDate: String,
    @SerializedName("learningType")
    val learningType: String,
    @SerializedName("orderDate")
    val orderDate: String,
    @SerializedName("paidAmount")
    val paidAmount: Int,
    @SerializedName("providerName")
    val providerName: String,
    @SerializedName("providerType")
    val providerType: String,
    @SerializedName("transcatioNo")
    val transcatioNo: Int,
    @SerializedName("typeOfMapping")
    val typeOfMapping: String
)