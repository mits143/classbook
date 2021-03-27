package com.app.classbook.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.SubTopicAdapter
import com.app.classbook.adapter.SubVideoAdapter
import com.app.classbook.model.response.*
import com.app.classbook.presenter.ActivitySubTopicPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivitySubTopicView
import kotlinx.android.synthetic.main.app_education_course_collection_list_2_activity.*
import retrofit2.Response

class SubTopic : AppCompatActivity(), ActivitySubTopicView.MainView {

    private lateinit var presenter: ActivitySubTopicPresenter
    private lateinit var adapter: SubTopicAdapter
    private lateinit var dataList: ArrayList<SubTopicData>

    lateinit var data: SubjectTopicData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_education_course_collection_list_2_activity)
        init()
    }

    private fun init() {
        presenter = ActivitySubTopicPresenter(this, this)
        if (intent.extras != null) {
            data = intent.getSerializableExtra("data") as SubjectTopicData //Obtaining data
            presenter.loadData(
                SharedPreference.authToken!!,
                data.smbId,
                data.subjectId,
                data.topicId
            )
        }
        titleNameTextView1.visibility = View.VISIBLE
        dataList = arrayListOf()
        val mLayoutManager = LinearLayoutManager(this)
        videoRecyclerView.layoutManager = mLayoutManager
        adapter = SubTopicAdapter(dataList)
        videoRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : SubTopicAdapter.OnItemClickListener {
            override fun onItemClick(
                view: View,
                obj: SubTopicData,
                position: Int
            ) {
                startActivity(
                    Intent(
                        this@SubTopic,
                        VideoActivity::class.java
                    ).putExtra("data", obj)
                )
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

    override fun onSuccess(responseModel: Response<SubTopicResponse>) {
        if (responseModel.body() != null) {
            if (responseModel.body()!!.topicListModel.isNotEmpty()) {
                dataList.clear()
                dataList.addAll(responseModel.body()!!.topicListModel)
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


