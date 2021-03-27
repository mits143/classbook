package com.app.classbook.view

import com.app.classbook.model.response.LoginResponse
import retrofit2.Response

interface LoginView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<LoginResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun postLoginData(
            token: String,
            Email: String,
            Password: String,
            DeviceId: String,
            FCMId: String
        )

        fun onStop()
    }
}