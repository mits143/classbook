package com.app.classbook.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.classbook.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        initViews()
        return view
    }

    private fun initViews() {
        linearProfile.setOnClickListener {
            startActivity(Intent(activity, ProfileActivity::class.java))
        }

        linearQRCode.setOnClickListener {
            startActivity(Intent(activity, QRCodeActivity::class.java))
        }

        linearAboutUs.setOnClickListener {
            startActivity(Intent(activity, UiWebViewWebViewBasicActivity::class.java))
        }
    }
}
