package com.app.classbook.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.*
import com.app.classbook.model.response.*
import com.app.classbook.presenter.ActivitySubTopicPresenter
import com.app.classbook.presenter.ActivityCourseVideoPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityCourseVideoView
import kotlinx.android.synthetic.main.activity_video.*
import kotlinx.android.synthetic.main.activity_video.bookLoading
import kotlinx.android.synthetic.main.activity_video.loader
import kotlinx.android.synthetic.main.app_education_course_collection_list_2_activity.*
import retrofit2.Response

class CoursesVideoActivity : AppCompatActivity(), ActivityCourseVideoView.MainView {

    private lateinit var presenter: ActivityCourseVideoPresenter
    private lateinit var adapter: CourseSubVideoAdapter
    private lateinit var dataList: ArrayList<CourseVideoListdata>

    lateinit var data: SubTopicData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        init()
    }

    private fun init() {
        presenter = ActivityCourseVideoPresenter(this, this)
        if (intent.extras != null) {
            val topicid = intent.extras!!.getInt("topicid")
            val courseId = intent.extras!!.getInt("courseId")
            val subtopicId = intent.extras!!.getInt("subtopicId")

            presenter.loadData(
                SharedPreference.authToken!!,
                topicid,
                courseId,
                subtopicId
            )
        }
        dataList = arrayListOf()
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager
        adapter = CourseSubVideoAdapter(dataList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : CourseSubVideoAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: CourseVideoListdata, position: Int) {
                andExoPlayerView.setSource(obj.url);
            }
        })
    }

    override fun showProgressbar() {
        loader.visibility = View.VISIBLE
        bookLoading.start()
    }

    override fun hideProgressbar() {
        loader.visibility = View.GONE
        bookLoading.stop()
    }

    override fun onSuccess(responseModel: Response<CourseVideoResponse>) {
        if (responseModel.body() != null) {
//            txtName.text = responseModel.body()!!.data.name
//            andExoPlayerView.setSource(responseModel.body()!!.data.videoLink);
            if (responseModel.body()!!.data != null && responseModel.body()!!.data.videoViewModelList.isNotEmpty()) {
                dataList.clear()
                dataList.addAll(responseModel.body()!!.data.videoViewModelList)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onError(errorCode: Int) {
        when (errorCode) {
            401 -> {
                Utils.showLoginAlert(this)
            }
            500 -> {
                Toast.makeText(
                    this,
                    getString(R.string.internal_server_error),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            else -> {
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }
}