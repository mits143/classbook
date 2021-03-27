package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.app.classbook.R
import com.app.classbook.adapter.ViewPagerAdapter
import com.app.classbook.fragment.ClassesFavFragment
import com.app.classbook.fragment.CoursesFavFragment
import com.app.classbook.fragment.ExpertFavFragment
import com.app.classbook.fragment.TeachersFavFragment

class SubCategoryDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subs_details)
    }
}