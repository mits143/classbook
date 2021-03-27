package com.app.classbook.model.response

data class AllClassesResponseItem(
    val cityName: String,
    val description: String,
    val establishmentDate: String,
    val favourite: Boolean,
    val id: Int,
    val image: String,
    val isFavourite: Boolean,
    val live: Any,
    val name: String,
    val online: String,
    val physical: String,
    val averageRating: String,
    val stateName: String,
    val numberOfBoards: String,
    val numberOfMediums: String,
    val numberOfStandards: String
)