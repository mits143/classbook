package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.classbook.R
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        ivBack.setOnClickListener {
            finish()
        }
    }
}
