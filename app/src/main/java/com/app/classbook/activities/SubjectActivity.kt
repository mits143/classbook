package com.app.classbook.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.SubjectAdapter
import com.app.classbook.model.response.Subject
import com.app.classbook.model.response.SubjectResponse
import com.app.classbook.presenter.ActivitySubjectPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivitySubjectView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.app_education_list_1_activity.*
import kotlinx.android.synthetic.main.app_education_list_1_activity.bookLoading
import kotlinx.android.synthetic.main.app_education_list_1_activity.loader
import retrofit2.Response

class SubjectActivity : AppCompatActivity(), ActivitySubjectView.MainView {

    private lateinit var presenter: ActivitySubjectPresenter
    private lateinit var adapter: SubjectAdapter
    private lateinit var dataList: ArrayList<Subject>

    private var id = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_education_list_1_activity)
        init()
    }

    private fun init() {
        presenter = ActivitySubjectPresenter(this, this)
        if (intent.extras != null) {
            id = intent.extras!!.getInt("id")

            presenter.loadData(
                SharedPreference.authToken!!,
                id
            )
        }
        val layoutManager = LinearLayoutManager(this)
        videoRecyclerView!!.layoutManager = layoutManager
        dataList = arrayListOf()
        adapter = SubjectAdapter(dataList)
        videoRecyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : SubjectAdapter.OnItemClickListener {
            override fun onItemClick(
                view: View,
                obj: Subject,
                position: Int,
                type: String
            ) {
                if (TextUtils.equals(type, "cart")) {
                    if (!TextUtils.equals(SharedPreference.authToken, "Default"))
                        presenter.addtocart(
                            SharedPreference.authToken!!,
                            obj.subjectMappingId,
                            "Subject",
                            "Distance"
                        )
                    else
                        Toast.makeText(
                            this@SubjectActivity,
                            "Please login to add in favorite",
                            Toast.LENGTH_LONG
                        ).show()
                } else {
                    startActivity(
                        Intent(
                            this@SubjectActivity,
                            TopicActivity::class.java
                        ).putExtra("data", obj)
                    )
                }
            }

        })

        ivSetting.setOnClickListener {
            finish()
        }
    }

    override fun showProgressbar() {
        loader.visibility = View.VISIBLE
        bookLoading.start()
    }

    override fun hideProgressbar() {
        loader.visibility = View.GONE
        bookLoading.stop()
    }

    override fun onSuccess(responseModel: Response<SubjectResponse>) {
        if (responseModel.body() != null) {
//            txtBoard.text = responseModel.body().data
//            txtMedium
//            txtStandard
            if (responseModel.body()!!.data!!.subjectList.isNotEmpty()) {
                dataList.clear()
                dataList.addAll(responseModel.body()!!.data!!.subjectList)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onSuccessCart(responseModel: Response<JsonObject>) {
        if (responseModel.body() != null) {
            Toast.makeText(this, responseModel.body()!!.get("message").asString, Toast.LENGTH_SHORT)
                .show()
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
