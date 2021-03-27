package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.classbook.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (intent.extras!=null){
            txtTitle.text = intent.extras!!.getString("title")
            txtDesc.text = intent.extras!!.getString("desc")
        }
    }
}