package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.app.classbook.R
import com.app.classbook.adapter.ViewPagerAdapter
import com.app.classbook.fragment.NotificationFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode=TabLayout.MODE_SCROLLABLE

        ivBack.setOnClickListener {
            finish()
        }
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        val generalFragment = NotificationFragment()
        adapter.addFragment(generalFragment, "General")

        val classesFragment = NotificationFragment()
        adapter.addFragment(classesFragment, "Classes")

        val coursesFragment = NotificationFragment()
        adapter.addFragment(coursesFragment, "Courses")

        val teacherFragment = NotificationFragment()
        adapter.addFragment(teacherFragment, "Teachers")

        val exFragment = NotificationFragment()
        adapter.addFragment(exFragment, "Experts")

        viewPager.adapter = adapter

    }
}