package com.app.classbook.view

import com.app.classbook.model.response.AllClassesResponse
import com.app.classbook.model.response.BannerResponse
import com.app.classbook.model.response.BoardsResponse
import com.app.classbook.model.response.CoursesListResponse
import retrofit2.Response

interface FragmentHomeView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccessBanner(responseModel: Response<BannerResponse>)
        fun onSuccessBoard(int: Int, responseModel: Response<BoardsResponse>)
        fun onSuccess(int: Int, responseModel: Response<AllClassesResponse>)
        fun onSuccessCoures(responseModel: Response<CoursesListResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadDataBanner(token: String)
        fun loadDataBoard(token: String)
        fun loadDataClasses(
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
            teacherId: Int
        )

        fun loadDataTeacher(
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
            teacherId: Int
        )

        fun loadDataCourses(
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
            teacherId: Int
        )

        fun loadDataExperts(
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
            teacherId: Int
        )

        fun onStop()
    }
}