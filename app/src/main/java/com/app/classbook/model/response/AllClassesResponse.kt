package com.app.classbook.model.response

data class AllClassesResponse(
    val `data`: List<AllClassesResponseItem>,
    val message: String
)