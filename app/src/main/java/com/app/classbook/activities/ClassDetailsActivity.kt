package com.app.classbook.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.ClassesDetailAdapter
//import com.app.classbook.adapter.ClassesDetailAdapter
import com.app.classbook.model.response.ClassDetailResponse
import com.app.classbook.model.response.SMBData
import com.app.classbook.model.response.SMBResponse
import com.app.classbook.presenter.ActivityClassDetailPresenter
import com.app.classbook.util.ItemDivider
import com.app.classbook.util.Utils
import com.app.classbook.util.Utils.showLoginAlert
import com.app.classbook.view.ActivityClassDetailView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_classes_details.*
import kotlinx.android.synthetic.main.activity_classes_details.bookLoading
import kotlinx.android.synthetic.main.activity_classes_details.ivCart
import kotlinx.android.synthetic.main.activity_classes_details.ivFav
import kotlinx.android.synthetic.main.activity_classes_details.ivNotification
import kotlinx.android.synthetic.main.activity_classes_details.loader
import retrofit2.Response

class ClassDetailsActivity : AppCompatActivity(), ActivityClassDetailView.MainView {

    private lateinit var presenter: ActivityClassDetailPresenter
    private lateinit var adapter: ClassesDetailAdapter
    private lateinit var dataList: ArrayList<SMBData>

    private var id = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes_details)
        init()
    }

    private fun init() {
        presenter = ActivityClassDetailPresenter(this, this)
        if (intent.extras != null) {
            className.text = intent.extras!!.getString("title")
            id = intent.extras!!.getInt("id")
            presenter.loadData(SharedPreference.authToken!!, id)
            presenter.loadSMB(SharedPreference.authToken!!, id)
        }

        val itemDecoration = ItemDivider(this, ItemDivider.VERTICAL)
        itemDecoration.setDividerColor(
            ContextCompat.getColor(
                this,
                R.color.md_grey_900
            )
        )
        recyclerViewCat.addItemDecoration(itemDecoration)
        val layoutManager = LinearLayoutManager(this)
        recyclerViewCat!!.layoutManager = layoutManager
        dataList = arrayListOf()
        adapter = ClassesDetailAdapter(dataList)
        recyclerViewCat!!.adapter = adapter
        adapter.setOnItemClickListener(object : ClassesDetailAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: SMBData, position: Int) {
                startActivity(
                    Intent(
                        this@ClassDetailsActivity,
                        SubjectActivity::class.java
                    ).putExtra("data", obj)
                )
            }
        })

        btnBack.setOnClickListener {
            finish()
        }
        ivNotification.setOnClickListener {
            if (TextUtils.equals(SharedPreference.authToken, "Default"))
                Utils.getBasicDialog(this)
            else
                startActivity(Intent(this, NotificationActivity::class.java))
        }
        ivFav.setOnClickListener {
            if (TextUtils.equals(SharedPreference.authToken, "Default"))
                Utils.getBasicDialog(this)
            else
                startActivity(Intent(this, FavouriteActivity::class.java))
        }
        ivCart.setOnClickListener {
            if (TextUtils.equals(SharedPreference.authToken, "Default"))
                Utils.getBasicDialog(this)
            else
                startActivity(Intent(this, CartActivity::class.java))
        }
//        classRatingBar.setOnClickListener {
//        }
        favFloatingActionButton.setOnClickListener {
            if (!TextUtils.equals(SharedPreference.authToken, "Default"))
                presenter.postAddToFav(SharedPreference.authToken!!, id, "class")
            else
                Toast.makeText(this, "Please login to add in favorite", Toast.LENGTH_LONG).show()
        }

        txtViewMore.setOnClickListener {
            if (TextUtils.equals(txtViewMore.text.toString(), "View More")) {
                txtDesc.maxLines = Integer.MAX_VALUE
                txtViewMore.text = "View Less"
            } else {
                txtDesc.maxLines = 3
                txtViewMore.text = "View More"
            }
        }
        txtViewMore1.setOnClickListener {
            if (TextUtils.equals(txtViewMore.text.toString(), "View More")) {
                txtCuriculum.maxLines = Integer.MAX_VALUE;
                txtViewMore1.text = "View Less"
            } else {
                txtCuriculum.maxLines = 3
                txtViewMore1.text = "View More"
            }
        }
        txtViewMore2.setOnClickListener {
            if (TextUtils.equals(txtViewMore.text.toString(), "View More")) {
                txtInstructor.maxLines = Integer.MAX_VALUE;
                txtViewMore2.text = "View Less"
            } else {
                txtInstructor.maxLines = 3
                txtViewMore2.text = "View More"
            }
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

    override fun onSuccess(responseModel: Response<ClassDetailResponse>) {
        if (responseModel.body() != null) {
            phoneTextView.text = responseModel.body()!!.data.className
            txtClassName.text = responseModel.body()!!.data.className
            txtAddress.text = responseModel.body()!!.data.address
            txtEmail.text = responseModel.body()!!.data.email
            txtContact.text = responseModel.body()!!.data.contactNo
            txtWebsite.text = responseModel.body()!!.data.website
            txtDesc.text = responseModel.body()!!.data.description
            txtCuriculum.text = responseModel.body()!!.data.curriculum
            if (responseModel.body()!!.data.instructorListModel.isNotEmpty())
                txtInstructor.text =
                    responseModel.body()!!.data.instructorListModel[0].instructorDescription
            if (!TextUtils.isEmpty(responseModel.body()!!.data.introductionVideoUrl))
                andExoPlayerView.setSource(responseModel.body()!!.data.introductionVideoUrl)
            classRatingBar.rating = responseModel.body()!!.data.averageRating.toFloat()

            if (responseModel.body()!!.data.favouriteCount == 1) {
                favFloatingActionButton.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.md_grey_500))
            } else {
                favFloatingActionButton.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
            }
            classRatingBar.setOnTouchListener(View.OnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    startActivity(
                        Intent(this, ReviewRatingActivity::class.java)
                            .putExtra(
                                "video",
                                responseModel.body()!!.data.averageRatingForVideoQuality
                            )
                            .putExtra(
                                "audio",
                                responseModel.body()!!.data.averageRatingForAudioQuality
                            )
                            .putExtra(
                                "points",
                                responseModel.body()!!.data.averageRatingForDescriptionOfPoints
                            )
                            .putExtra(
                                "videos",
                                responseModel.body()!!.data.averageRatingForLengthOfVideo
                            )
                            .putExtra(
                                "syllabus",
                                responseModel.body()!!.data.averageRatingForSyllabus
                            )
                    )
                }
                return@OnTouchListener true
            })
        }
    }

    override fun onSuccessSMB(responseModel: Response<SMBResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.data.isNotEmpty()) {
            dataList.clear()
            dataList.addAll(responseModel.body()!!.data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onSuccessAddToFav(responseModel: Response<JsonObject>) {
        Toast.makeText(this, responseModel.body()!!.get("message").asString, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onError(errorCode: Int) {
        when (errorCode) {
            401 -> {
                showLoginAlert(this)
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


