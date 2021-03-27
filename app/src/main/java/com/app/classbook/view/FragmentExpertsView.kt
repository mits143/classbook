package com.app.classbook.view

import com.app.classbook.model.response.AllClassesResponse
import retrofit2.Response

interface FragmentExpertsView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<AllClassesResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String,
            boardId: Int,
            cityId: Int,
            classId: Int,
            courseCategoryId: Int,
            expertiesId: Int,
            mediumId: Int,
            onlineLive: Boolean,
            onlineLivePhysical: Boolean,
            pageIndex: Int,
            pageSize: Int,
            searchName: String,
            standardId: Int,
            stateId: Int,
            teacherId: Int)
        fun onStop()
    }
}