package com.app.classbook.view

import com.app.classbook.model.response.ClassDetailResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface ActivityTeacherDetailView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<ClassDetailResponse>)
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