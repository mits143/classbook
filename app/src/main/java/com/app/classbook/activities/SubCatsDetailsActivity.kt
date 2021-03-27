package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.app.classbook.R
import com.app.classbook.adapter.ViewPagerAdapter
import com.app.classbook.fragment.AppECommerceOrder4Fragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_notification.ivBack
import kotlinx.android.synthetic.main.activity_notification.tabLayout
import kotlinx.android.synthetic.main.activity_notification.viewPager

class SubCatsDetailsActivity : AppCompatActivity() {
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

        val classesFragment = AppECommerceOrder4Fragment()
        adapter.addFragment(classesFragment, "Classes")

        val coursesFragment = AppECommerceOrder4Fragment()
        adapter.addFragment(coursesFragment, "Courses")

        val teacherFragment = AppECommerceOrder4Fragment()
        adapter.addFragment(teacherFragment, "Teachers")

        val exFragment = AppECommerceOrder4Fragment()
        adapter.addFragment(exFragment, "Experts")

        viewPager.adapter = adapter

    }
}