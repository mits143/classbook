package com.app.classbook.model.response

data class SubscriptionResponseItem(
    val boardName: String,
    val enityName: String,
    val expireDate: String,
    val learningType: String,
    val mediumName: String,
    val paidAmount: Int,
    val providerName: String,
    val providerType: String,
    val standardsName: String,
    val subscriptionDate: String,
    val typeOfMapping: String
)