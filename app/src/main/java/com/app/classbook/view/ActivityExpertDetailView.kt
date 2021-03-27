package com.app.classbook.view

import com.app.classbook.model.response.ExpertCareerDetailResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface ActivityExpertDetailView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<ExpertCareerDetailResponse>)
        fun onSuccessAddToFav(responseModel: Response<JsonObject>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String,
            id: Int
        )
        fun postAddToFav(
            token: String,
            EntityId: Int,
            EntityName: String
        )

        fun onStop()
    }
}