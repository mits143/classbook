package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class LevelData(
    @SerializedName("levelDifferenceIncomeModel")
    val levelDifferenceIncomeModel: List<LevelDifferenceIncomeModel>,
    @SerializedName("residualIncomeModel")
    val residualIncomeModel: List<ResidualIncomeModel>,
    @SerializedName("royaltyIncomeModel")
    val royaltyIncomeModel: List<RoyaltyIncomeModel>
)