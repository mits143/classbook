package com.app.classbook.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.model.response.LoginResponse
import com.app.classbook.presenter.LoginPresenter
import com.app.classbook.util.Utils
import com.app.classbook.util.Utils.isValidEmail
import com.app.classbook.view.LoginView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Response

class LoginActivity : AppCompatActivity(), LoginView.MainView {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        presenter = LoginPresenter(this, this)
        loginButton.setOnClickListener {
            submitData()
        }
        textViewSkip.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        forgotButton.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        signuptButton.setOnClickListener {
            showRegOption()
        }
    }

    private fun showRegOption() {
        val dialogView: View = layoutInflater.inflate(R.layout.activity_registration_type, null)
        val dialog = BottomSheetDialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)

        val regClass = dialogView.findViewById(R.id.regClass) as TextView
        val regSchool = dialogView.findViewById(R.id.regSchool) as TextView
        val regStudent = dialogView.findViewById(R.id.regStudent) as TextView
        val regTeacher = dialogView.findViewById(R.id.regTeacher) as TextView
        val regExpert = dialogView.findViewById(R.id.regExpert) as TextView

        regClass.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, SignUpActivity::class.java).putExtra("type", "class"))
        }

        regSchool.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, SignUpActivity::class.java).putExtra("type", "school"))
        }

        regStudent.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, SignUpActivity::class.java).putExtra("type", "student"))
        }

        regTeacher.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, SignUpActivity::class.java).putExtra("type", "teacher"))
        }

        regExpert.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, SignUpActivity::class.java).putExtra("type", "expert"))
        }
        dialog.show()
    }

    private fun submitData() {
        if (!isValidEmail(inputEmail.text.toString().trim())) {
            inputEmail.error = "Enter valid email address"
            inputEmail.requestFocus()
            return
        }
        if (TextUtils.isEmpty(inputPassword.text.toString().trim())) {
            inputPassword.error = "Invalid password"
            inputPassword.requestFocus()
            return
        }
        presenter.postLoginData(
            SharedPreference.authToken!!,
            inputEmail.text.toString().trim(),
            inputPassword.text.toString().trim(),
            Utils.deviceId(this),
            SharedPreference.token!!
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

    override fun onSuccess(responseModel: Response<LoginResponse>) {
        if (responseModel.body() != null) {
            SharedPreference.isLogin = true
            SharedPreference.userId = responseModel.body()!!.data.userId
            SharedPreference.userName = responseModel.body()!!.data.email
            SharedPreference.authToken = responseModel.body()!!.data.authorizeTokenKey
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    override fun onError(errorCode: Int) {
        when (errorCode) {
            401 -> {
                Toast.makeText(this, getString(R.string.invalid_email_pwd), Toast.LENGTH_SHORT)
                    .show()
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
