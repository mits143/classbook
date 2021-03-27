package com.app.classbook.view

import com.app.classbook.model.response.CourseDetailResponse
import retrofit2.Response

interface ActivityCourseDetailView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<CourseDetailResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String,
            id: Int
        )
        fun onStop()
    }
}