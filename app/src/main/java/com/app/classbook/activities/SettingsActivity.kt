package com.app.classbook.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.util.Constant.Companion.ABOUT_US
import com.app.classbook.util.Constant.Companion.CONTACTUSAPI
import com.app.classbook.util.Constant.Companion.COOKIES
import com.app.classbook.util.Constant.Companion.DISCLAMIER
import com.app.classbook.util.Constant.Companion.EULAPOLICIESAPI
import com.app.classbook.util.Constant.Companion.HOWITWORKSAPI
import com.app.classbook.util.Constant.Companion.PRIVACY_POLICY
import com.app.classbook.util.Constant.Companion.RETURNANDREFUNDPOLICY
import com.app.classbook.util.Constant.Companion.TERMSOFCONDITIONSAPI
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_settings)
        initViews()
    }

    private fun initViews() {
        if (TextUtils.equals(SharedPreference.authToken, "Default"))
            linearLogout.visibility = View.GONE
        else
            linearLogout.visibility = View.VISIBLE

        ivBack.setOnClickListener {
            finish()
        }
        linearProfile.setOnClickListener {
            if (TextUtils.equals(SharedPreference.authToken, "Default"))
                startActivity(Intent(this, LoginActivity::class.java))
            else
                startActivity(Intent(this, ProfileActivity::class.java))
        }

        linearSubs.setOnClickListener {
            if (TextUtils.equals(SharedPreference.authToken, "Default"))
                startActivity(Intent(this, LoginActivity::class.java))
            else
                startActivity(Intent(this, SubscriptionsActivity::class.java))
        }

        linearQuiz.setOnClickListener {
            if (TextUtils.equals(SharedPreference.authToken, "Default"))
                startActivity(Intent(this, LoginActivity::class.java))
            else
                startActivity(Intent(this, MyQuizActivity::class.java))
        }

        linearChat.setOnClickListener {
            startActivity(Intent(this, ChatListActivity::class.java))
        }

        linearChangePassword.setOnClickListener {
            if (TextUtils.equals(SharedPreference.authToken, "Default"))
                startActivity(Intent(this, LoginActivity::class.java))
            else
                startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

        linearTransaction.setOnClickListener {
            startActivity(Intent(this, TransactionActivity::class.java))
        }

        linearQRCode.setOnClickListener {
            if (TextUtils.equals(SharedPreference.authToken, "Default"))
                startActivity(Intent(this, LoginActivity::class.java))
            else
                startActivity(Intent(this, QRCodeActivity::class.java))
        }

        linearAboutUs.setOnClickListener {
            val intent = Intent(this, UiWebViewWebViewBasicActivity::class.java)
            intent.putExtra("title", "About Us")
            intent.putExtra("url", ABOUT_US)
            startActivity(intent)
        }

        linearTerms.setOnClickListener {
            val intent = Intent(this, UiWebViewWebViewBasicActivity::class.java)
            intent.putExtra("title", "Terms and Conditions")
            intent.putExtra("url", TERMSOFCONDITIONSAPI)
            startActivity(intent)
        }

        linearPrivacy.setOnClickListener {
            val intent = Intent(this, UiWebViewWebViewBasicActivity::class.java)
            intent.putExtra("title", "Privacy Policy")
            intent.putExtra("url", PRIVACY_POLICY)
            startActivity(intent)
        }

        linearWorks.setOnClickListener {
            val intent = Intent(this, UiWebViewWebViewBasicActivity::class.java)
            intent.putExtra("title", "How it works")
            intent.putExtra("url", HOWITWORKSAPI)
            startActivity(intent)
        }

        linearCookie.setOnClickListener {
            val intent = Intent(this, UiWebViewWebViewBasicActivity::class.java)
            intent.putExtra("title", "Cookies")
            intent.putExtra("url", COOKIES)
            startActivity(intent)
        }

        linearDisclaimer.setOnClickListener {
            val intent = Intent(this, UiWebViewWebViewBasicActivity::class.java)
            intent.putExtra("title", "Disclaimer")
            intent.putExtra("url", DISCLAMIER)
            startActivity(intent)
        }

        linearEULA.setOnClickListener {
            val intent = Intent(this, UiWebViewWebViewBasicActivity::class.java)
            intent.putExtra("title", "EULA Policies")
            intent.putExtra("url", EULAPOLICIESAPI)
            startActivity(intent)
        }

        linearRefund.setOnClickListener {
            val intent = Intent(this, UiWebViewWebViewBasicActivity::class.java)
            intent.putExtra("title", "Return and Refund policy")
            intent.putExtra("url", RETURNANDREFUNDPOLICY)
            startActivity(intent)
        }

        linearContact.setOnClickListener {
            val intent = Intent(this, UiWebViewWebViewBasicActivity::class.java)
            intent.putExtra("title", "Contact Us")
            intent.putExtra("url", CONTACTUSAPI)
            startActivity(intent)
        }

        linearLogout.setOnClickListener {
            getBasicDialog()
        }
    }

    override fun onResume() {
        super.onResume()
        if (TextUtils.equals(SharedPreference.authToken, "Default"))
            linearLogout.visibility = View.GONE
        else
            linearLogout.visibility = View.VISIBLE
    }

    private fun getBasicDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Do you really want to Logout?")
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.cancel()
                SharedPreference.authToken = "Default"
                SharedPreference.isLogin = false
                val intent = Intent(this@SettingsActivity, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finishAffinity()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
            .setIcon(R.drawable.baseline_warning_black_24)
            .show()
    }
}
