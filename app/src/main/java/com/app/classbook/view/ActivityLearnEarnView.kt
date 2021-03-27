package com.app.classbook.view

import com.app.classbook.model.response.LearnEarnResponse
import com.app.classbook.model.response.LevelResponse
import retrofit2.Response

interface ActivityLearnEarnView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<LearnEarnResponse>)
        fun onSuccessLevel(responseModel: Response<LevelResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadDataReg(
            token: String
        )
        fun loadDataLearnEarn(
            token: String
        )
        fun loadData(
            token: String
        )
        fun onStop()
    }
}