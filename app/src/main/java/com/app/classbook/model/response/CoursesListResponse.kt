package com.app.classbook.model.response

data class CoursesListResponse(
    val `data`: List<CoursesListResponseItem>,
    val message: String
)