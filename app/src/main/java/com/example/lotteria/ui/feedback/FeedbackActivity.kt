package com.example.lotteria.ui.feedback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.commit
import com.example.lotteria.R
import com.example.lotteria.databinding.ActivityFeedbackBinding
import com.example.lotteria.ui.setting.SettingPageFragment

class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        binding.apply {
            feedbackButton.setOnClickListener {
                val mFragmentManager = supportFragmentManager
                val mSettingFragment = SettingPageFragment()
                mFragmentManager.commit {
                    addToBackStack(null)
                    add(R.id.setting_page, mSettingFragment, SettingPageFragment::class.java.simpleName)
                }
            }
        }
    }
}