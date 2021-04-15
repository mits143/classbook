package com.app.classbook.view

import com.app.classbook.model.response.BoardsResponse
import com.app.classbook.model.response.LoginResponse
import com.app.classbook.model.response.StateResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface RegisterView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccessCommonData(int: Int, responseModel: Response<StateResponse>)
        fun onSuccessBoard(int: Int, responseModel: Response<BoardsResponse>)
        fun onSuccess(responseModel: Response<LoginResponse>)
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

        fun getPincodes(
            token: String, id: Int
        )

        fun getBoard(
            token: String
        )

        fun getMedium(
            token: String
        )

        fun getStandard(token: String)
        fun getSubject()
        fun getSubjectSpecialities()
        fun getCourses(token: String)
        fun postClassRegisterData(
            file: MultipartBody.Part,
            files: ArrayList<MultipartBody.Part>,
            data: RequestBody,
            DeviceId: RequestBody,
            FCMId: RequestBody
        )

        fun postSchoolRegisterData(
            file: MultipartBody.Part,
            files: ArrayList<MultipartBody.Part>,
            data: RequestBody,
            DeviceId: RequestBody,
            FCMId: RequestBody
        )

        fun postStudentRegisterData(
            file: MultipartBody.Part,
            data: RequestBody,
            DeviceId: RequestBody,
            FCMId: RequestBody
        )

        fun postTeacherRegisterData(
            file: MultipartBody.Part,
            data: RequestBody,
            DeviceId: RequestBody,
            FCMId: RequestBody
        )

        fun postExpertRegisterData(
            file: MultipartBody.Part,
            data: RequestBody,
            DeviceId: RequestBody,
            FCMId: RequestBody
        )

        fun onStop()
    }
}