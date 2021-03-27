package com.app.classbook.view

import com.app.classbook.model.response.CourseVideoResponse
import com.app.classbook.model.response.SubTopicResponse
import com.app.classbook.model.response.SubjectVideoResponse
import com.app.classbook.model.response.TopicSubTopicResponse
import retrofit2.Response

interface ActivityCourseVideoView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<CourseVideoResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String,
            topicId: Int,
            courseId: Int,
            subtopicId: Int,
        )

        fun onStop()
    }
}