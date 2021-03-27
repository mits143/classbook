package com.app.classbook.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.classbook.R
import kotlinx.android.synthetic.main.ui_web_view_web_view_basic_activity.*

class UiWebViewWebViewBasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_web_view_web_view_basic_activity)
        init()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun init() {
        if (intent.extras != null) {
            val title = intent.extras!!.getString("title")
            titleNameTextView.text = title
            val aboutUs = intent.extras!!.getString("url")
            webview.settings.loadsImagesAutomatically = true;
            webview.settings.javaScriptEnabled = true;
            webview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY;
            webview.loadUrl(aboutUs!!)
        }
        ivSetting.setOnClickListener {
            finish()
        }
    }
}
