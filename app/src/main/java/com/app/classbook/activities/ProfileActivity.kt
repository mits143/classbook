package com.app.classbook.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.model.response.ProfileResponse
import com.app.classbook.presenter.ActivityProfilePresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityProfileView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.ivBack
import retrofit2.Response

class ProfileActivity : AppCompatActivity(), ActivityProfileView.MainView {

    private lateinit var presenter: ActivityProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
    }

    private fun init() {
        presenter = ActivityProfilePresenter(this, this)
        presenter.loadData(SharedPreference.authToken!!)

        ivBack.setOnClickListener {
            finish()
        }

        editFAB.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
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

    override fun onSuccess(responseModel: Response<ProfileResponse>) {
        if (responseModel.body() != null) {
//            tvClasses.text = responseModel.body()!!.data.boardName
//            tvCourses.text = responseModel.body()!!.data.mediumName
//            tvTeachers.text = responseModel.body()!!.data.standardName
            Glide.with(this).load(responseModel.body()!!.data.imageUrl).into(profileImageView)
            emailTextView.text = responseModel.body()!!.data.email
            phoneTextView.text = responseModel.body()!!.data.contactNo
            websiteTextView.text = responseModel.body()!!.data.boardName
            textView27.text = responseModel.body()!!.data.mediumName
            tvMedium.text = responseModel.body()!!.data.standardName
            tvDOB.text = responseModel.body()!!.data.dob
        }
    }

    override fun onError(errorCode: Int) {
        when (errorCode) {
            401 -> {
                Utils.showLoginAlert(this)
            }
            500 -> {
                Toast.makeText(this, getString(R.string.internal_server_error), Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
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
