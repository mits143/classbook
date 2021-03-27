package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.classbook.R
import kotlinx.android.synthetic.main.activity_q_r_code.*

class QRCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r_code)

        ivBack.setOnClickListener {
            finish()
        }
    }
}
