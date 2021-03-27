package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.app.classbook.R
import com.app.classbook.adapter.ViewPagerAdapter
import com.app.classbook.fragment.SubsClassesFragment
import com.app.classbook.fragment.SubsCoursesFragment
import com.app.classbook.fragment.SubsExpertFragment
import com.app.classbook.fragment.SubsTeacherFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_notification.ivBack
import kotlinx.android.synthetic.main.activity_notification.tabLayout
import kotlinx.android.synthetic.main.activity_notification.viewPager

class SubscriptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subs)
        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        val classesFragment = SubsClassesFragment()
        adapter.addFragment(classesFragment, "Classes")

        val coursesFragment = SubsCoursesFragment()
        adapter.addFragment(coursesFragment, "Courses")

        val teacherFragment = SubsTeacherFragment()
        adapter.addFragment(teacherFragment, "Teachers")

        val exFragment = SubsExpertFragment()
        adapter.addFragment(exFragment, "Experts")

        viewPager.adapter = adapter

    }
}