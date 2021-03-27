package com.app.classbook.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.classbook.R
import com.app.classbook.fragment.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == android.R.id.home) {
                finish()
            } else {
                when (item.itemId) {
                    R.id.homeMenu -> {
                        loadFragment(HomeFragment())
                    }
                    R.id.classesMenu -> {
                        loadFragment(ClassesListFragment())
                    }
                    R.id.coursesMenu -> {
                        loadFragment(CoursesListFragment())
                    }
                    R.id.teachersMenu -> {
                        loadFragment(TeachersListFragment())
                    }
                    R.id.expertMenu -> {
                        loadFragment(ExpertListFragment())
                    }
                }
            }

            true
        }

        loadFragment(HomeFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        this.supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commitAllowingStateLoss()
    }

    public fun viewAll(id: Int) {
        if (id == 1) {
            bottomNavigationView.selectedItemId = R.id.classesMenu
            loadFragment(ClassesListFragment())
        }
        if (id == 2) {
            bottomNavigationView.selectedItemId = R.id.teachersMenu
            loadFragment(TeachersListFragment())
        }
        if (id == 3) {
            bottomNavigationView.selectedItemId = R.id.coursesMenu
            loadFragment(CoursesListFragment())
        }
        if (id == 4) {
            bottomNavigationView.selectedItemId = R.id.expertMenu
            loadFragment(ExpertListFragment())
        }
    }
}
