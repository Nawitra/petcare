package com.seventhgroup.petcare.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.seventhgroup.petcare.adapter.OnboardingPagerAdapter
import com.seventhgroup.petcare.databinding.ActivityOnboardingBinding

class OnboardingActivity: AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPrefs: SharedPreferences = getSharedPreferences("onboarding_completed",
            Context.MODE_PRIVATE)

        if (sharedPrefs.getBoolean("onboarding_completed", false)) {
            val mIntent = Intent(this@OnboardingActivity, MainActivity::class.java)
            startActivity(mIntent)
        } else {
            val pagerAdapter = OnboardingPagerAdapter(this@OnboardingActivity)
            binding.viewpagerOnboarding.adapter = pagerAdapter
            val editor: SharedPreferences.Editor = sharedPrefs.edit()
            editor.putBoolean("onboarding_completed", true)
            editor.apply()
        }
    }
}