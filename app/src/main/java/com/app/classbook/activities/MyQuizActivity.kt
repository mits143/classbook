package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.app.classbook.R
import com.app.classbook.adapter.ViewPagerAdapter
import com.app.classbook.fragment.QuizFragment
import kotlinx.android.synthetic.main.my_quiz_activity.*

class MyQuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_quiz_activity)
        initView()

        ivSetting.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initView() {
        setupViewPager(viewPager = viewPager)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(QuizFragment(), "Aptitude Quiz")
        adapter.addFragment(QuizFragment(), "Therotical Quiz")
        viewPager.adapter = adapter
    }
}
