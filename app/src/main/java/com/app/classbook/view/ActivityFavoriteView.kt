package com.app.classbook.view

import com.app.classbook.model.response.ClassDetailResponse
import com.app.classbook.model.response.FavoriteResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface ActivityFavoriteView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<FavoriteResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String
        )

        fun onStop()
    }
}