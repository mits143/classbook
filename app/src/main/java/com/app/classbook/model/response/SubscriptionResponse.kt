package com.app.classbook.model.response

data class SubscriptionResponse(
        val `data`: List<SubscriptionResponseItem>,
        val message: String
)