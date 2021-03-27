package com.app.classbook.view

import com.app.classbook.model.response.ClassDetailResponse
import com.app.classbook.model.response.SMBResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface ActivityClassDetailView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<ClassDetailResponse>)
        fun onSuccessSMB(responseModel: Response<SMBResponse>)
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
        fun loadSMB(
            token: String,
            id: Int
        )

        fun onStop()
    }
}