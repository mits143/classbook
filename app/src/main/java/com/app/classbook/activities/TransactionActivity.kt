package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.app.classbook.R
import com.app.classbook.adapter.ViewPagerAdapter
import com.app.classbook.fragment.TransactionClassesFragment
import com.app.classbook.fragment.TransactionCoursesFragment
import com.app.classbook.fragment.TransactionExpertsFragment
import com.app.classbook.fragment.TransactionTeacherFragment
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        val classesFragment = TransactionClassesFragment()
        adapter.addFragment(classesFragment, "Classes")

        val coursesFragment = TransactionCoursesFragment()
        adapter.addFragment(coursesFragment, "Courses")

        val teacherFragment = TransactionTeacherFragment()
        adapter.addFragment(teacherFragment, "Teachers")

        val exFragment = TransactionExpertsFragment()
        adapter.addFragment(exFragment, "Experts")

        viewPager.adapter = adapter

        ivBack.setOnClickListener {
            finish()
        }

    }
}
