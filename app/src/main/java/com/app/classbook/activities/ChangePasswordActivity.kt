package com.app.classbook.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.presenter.ChangePwdPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ChangePwdView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_change_password.ivBack
import kotlinx.android.synthetic.main.activity_transaction.*
import retrofit2.Response


class ChangePasswordActivity : AppCompatActivity(), ChangePwdView.MainView {

    private lateinit var presenter: ChangePwdPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        init()
    }

    fun init() {
        presenter = ChangePwdPresenter(this, this)
        ivBack.setOnClickListener {
            finish()
        }
        btnChangePassword.setOnClickListener {
            submit()
        }
    }

    private fun submit() {
        if (TextUtils.isEmpty(inputOldPassword.text.toString().trim())) {
            inputOldPassword.error = "Enter old password"
            inputOldPassword.requestFocus()
            return
        }
        if (TextUtils.isEmpty(inputNewPassword.text.toString().trim())) {
            inputNewPassword.error = "Enter new password"
            inputNewPassword.requestFocus()
            return
        }
        if (!TextUtils.equals(
                inputNewPassword.text.toString().trim(),
                inputConfirmPassword.text.toString().trim()
            )
        ) {
            inputConfirmPassword.error = "Password mismatch"
            inputConfirmPassword.requestFocus()
            return
        }
        presenter.loadData(
            SharedPreference.authToken!!,
            inputOldPassword.text.toString().trim(),
            inputNewPassword.text.toString().trim()
        )
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
            val intent = Intent(this@ChangePasswordActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
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