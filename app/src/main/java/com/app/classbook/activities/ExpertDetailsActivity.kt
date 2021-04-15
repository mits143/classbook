package com.app.classbook.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.model.response.ExpertCareerDetailResponse
import com.app.classbook.presenter.ActivityExpertDetailPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityExpertDetailView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_expert_details.*
import kotlinx.android.synthetic.main.activity_expert_details.btnBack
import kotlinx.android.synthetic.main.activity_expert_details.ivCart
import kotlinx.android.synthetic.main.activity_expert_details.ivFav
import kotlinx.android.synthetic.main.activity_expert_details.ivNotification
import retrofit2.Response

class ExpertDetailsActivity : AppCompatActivity(), ActivityExpertDetailView.MainView {

    private lateinit var presenter: ActivityExpertDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expert_details)
        init()
    }

    private fun init() {
        presenter = ActivityExpertDetailPresenter(this, this)
        if (intent.extras != null) {
            expertName.text = intent.extras!!.getString("title")
            presenter.loadData(SharedPreference.authToken!!, intent.extras!!.getInt("id"))
        }

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
    }

    override fun showProgressbar() {
        loader.visibility = View.VISIBLE
        bookLoading.start()
    }

    override fun hideProgressbar() {
        loader.visibility = View.GONE
        bookLoading.stop()
    }

    override fun onSuccess(responseModel: Response<ExpertCareerDetailResponse>) {

    }

    override fun onSuccessAddToFav(responseModel: Response<JsonObject>) {

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
