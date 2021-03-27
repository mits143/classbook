package com.app.classbook.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.app.classbook.R
import com.app.classbook.adapter.ViewPagerAdapter
import com.app.classbook.fragment.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_favourite.ivBack
import kotlinx.android.synthetic.main.activity_favourite.tabLayout
import kotlinx.android.synthetic.main.activity_favourite.viewPager


class FavouriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode= TabLayout.MODE_SCROLLABLE

        ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        val classesFragment = ClassesFavFragment()
        adapter.addFragment(classesFragment, "Classes")

        val coursesFragment = CoursesFavFragment()
        adapter.addFragment(coursesFragment, "Courses")

        val teacherFragment = TeachersFavFragment()
        adapter.addFragment(teacherFragment, "Teachers")

        val exFragment = ExpertFavFragment()
        adapter.addFragment(exFragment, "Experts")

        viewPager.adapter = adapter
    }
}