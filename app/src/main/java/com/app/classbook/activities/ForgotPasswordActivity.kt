package com.app.classbook.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.classbook.R
import com.app.classbook.presenter.ForgotPwdPresenter
import com.app.classbook.presenter.RegisterPresenter
import com.app.classbook.util.Utils
import com.app.classbook.util.Utils.isValidEmail
import com.app.classbook.view.ForgotPwdView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_forgot_password.*
import retrofit2.Response

class ForgotPasswordActivity : AppCompatActivity(), ForgotPwdView.MainView {

    private lateinit var presenter: ForgotPwdPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        init()
    }

    fun init() {
        presenter = ForgotPwdPresenter(this, this)
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        resetButton.setOnClickListener {
            submit()
        }
    }

    private fun submit() {
        if (!isValidEmail(etEmail.text.toString().trim())) {
            etEmail.error = "Enter valid email"
            etEmail.requestFocus()
            return
        }
        presenter.loadData(etEmail.text.toString().trim())
    }

    override fun showProgressbar() {
        loader.visibility = View.VISIBLE
        bookLoading.start()
    }

    override fun hideProgressbar() {
        loader.visibility = View.GONE
        bookLoading.stop()
    }

    override fun onSuccess(responseModel: Response<JsonObject>) {
        if (responseModel.body() != null) {
            Toast.makeText(this, responseModel.body()!!.get("message").asString, Toast.LENGTH_SHORT)
                    .show()
        }
        finish()
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