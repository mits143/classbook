package com.app.classbook.view

import com.app.classbook.model.response.BoardsResponse
import com.app.classbook.model.response.LoginResponse
import com.app.classbook.model.response.StateResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface FilterView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccessCommonData(int: Int, responseModel: Response<StateResponse>)
        fun onSuccessBoard(int: Int, responseModel: Response<BoardsResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun getStates(
            token: String
        )

        fun getCities(
            token: String, id: Int
        )

        fun getBoard(
            token: String
        )

        fun getMedium(
            token: String
        )

        fun getStandard(token: String)

        fun getCourses(
            token: String
        )

        fun onStop()
    }
}