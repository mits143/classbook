package com.app.classbook.activities

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.util.Utils
import kotlinx.android.synthetic.main.feature_splash_general_splash_screen_1_layout_activity.*

class SplashActivity : AppCompatActivity() {

    companion object {
        const val DELAY_TIME = 3000L
    }

    private var isRunning: Boolean = false
    private var runnable: Runnable? = null
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feature_splash_general_splash_screen_1_layout_activity)
        initDataBindings()
    }

    private fun initDataBindings() {
        Utils.setImageToImageView(this, s1bgImageVIew, R.drawable.back)
        s1bgImageVIew.alpha = 0.6f
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        //Here you can get the size!
        if (!isRunning) {
            isRunning = true

            iconImageView.animate().alpha(1f).setDuration(3000)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animator: Animator?) {

                    }

                    override fun onAnimationEnd(animator: Animator?) {
                        navigateToLandingScreen()
                    }

                    override fun onAnimationCancel(animator: Animator?) {

                    }

                    override fun onAnimationRepeat(animator: Animator?) {

                    }
                }).start()

        }
    }

    private fun navigateToLandingScreen() {
        runnable = Runnable {
            if (SharedPreference.isLogin) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                SharedPreference.authToken = "Default"
                startActivity(Intent(this, LoginActivity::class.java))
            }

            finish()
        }
        handler.postDelayed(runnable!!, 1000)
    }
}
