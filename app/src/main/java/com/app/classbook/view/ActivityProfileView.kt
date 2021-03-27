package com.app.classbook.view

import com.app.classbook.model.response.ProfileResponse
import retrofit2.Response

interface ActivityProfileView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<ProfileResponse>)
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