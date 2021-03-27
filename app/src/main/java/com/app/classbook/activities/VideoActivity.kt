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
import com.app.classbook.adapter.SubTopicAdapter
import com.app.classbook.adapter.SubVideoAdapter
import com.app.classbook.adapter.SubjectAdapter
import com.app.classbook.adapter.TopicAdapter
import com.app.classbook.model.response.*
import com.app.classbook.presenter.ActivitySubTopicPresenter
import com.app.classbook.presenter.ActivitySubVideoPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivitySubVideoView
import kotlinx.android.synthetic.main.activity_video.*
import kotlinx.android.synthetic.main.activity_video.bookLoading
import kotlinx.android.synthetic.main.activity_video.loader
import kotlinx.android.synthetic.main.app_education_course_collection_list_2_activity.*
import retrofit2.Response

class VideoActivity : AppCompatActivity(), ActivitySubVideoView.MainView {

    private lateinit var presenter: ActivitySubVideoPresenter
    private lateinit var adapter: SubVideoAdapter
    private lateinit var dataList: ArrayList<SubjectVideoData>

    lateinit var data: SubTopicData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        init()
    }

    private fun init() {
        presenter = ActivitySubVideoPresenter(this, this)
        if (intent.extras != null) {
            data = intent.getSerializableExtra("data") as SubTopicData //Obtaining data

            presenter.loadData(SharedPreference.authToken!!, data.topicId, data.smbId, data.subjectId, data.subTopicId)
        }
        dataList = arrayListOf()
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager
        adapter = SubVideoAdapter(dataList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : SubVideoAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: SubjectVideoData, position: Int) {
                andExoPlayerView.setSource(obj.videoUrl);
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

    override fun onSuccess(responseModel: Response<SubjectVideoResponse>) {
        if (responseModel.body() != null) {
//            txtName.text = responseModel.body()!!.data.name
//            andExoPlayerView.setSource(responseModel.body()!!.data.videoLink);
            if (responseModel.body()!!.data != null && responseModel.body()!!.data.isNotEmpty()) {
                dataList.clear()
                dataList.addAll(responseModel.body()!!.data)
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