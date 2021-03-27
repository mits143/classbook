package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.app.classbook.R
import com.app.classbook.adapter.ViewPagerAdapter
import com.app.classbook.fragment.TransactionFragment
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

        val classesFragment = TransactionFragment()
        adapter.addFragment(classesFragment, "Classes")

        val coursesFragment = TransactionFragment()
        adapter.addFragment(coursesFragment, "Courses")

        val teacherFragment = TransactionFragment()
        adapter.addFragment(teacherFragment, "Teachers")

        val exFragment = TransactionFragment()
        adapter.addFragment(exFragment, "Experts")

        viewPager.adapter = adapter

        ivBack.setOnClickListener {
            finish()
        }

    }
}
