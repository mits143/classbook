package com.app.classbook.view

import com.app.classbook.model.response.TrainingVideoResponse
import retrofit2.Response

interface ActivityTrainingView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<TrainingVideoResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadDataReg(
            token: String
        )
        fun onStop()
    }
}