package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.classbook.R
import kotlinx.android.synthetic.main.activity_review_rating.*

class ReviewRatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_rating)
        init()
    }

    private fun init() {
        if (intent.extras != null) {
            videoRatingBar.rating = intent.extras!!.getInt("video").toFloat()
            audioRatingBar.rating = intent.extras!!.getInt("audio").toFloat()
            descRatingBar.rating = intent.extras!!.getInt("points").toFloat()
            lengthRatingBar.rating = intent.extras!!.getInt("videos").toFloat()
            syllabusRatingBar.rating = intent.extras!!.getInt("syllabus").toFloat()
        }
    }
}