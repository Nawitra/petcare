package com.seventhgroup.petcare.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.seventhgroup.petcare.R
import com.seventhgroup.petcare.databinding.SplashScreenBinding

class SplashScreenActivity: AppCompatActivity() {
    private lateinit var binding: SplashScreenBinding
    
    private val delay: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        supportActionBar?.hide()
        
        val bgImg: ImageView = binding.imageSplashscreenLogo
        val anim = AnimationUtils.loadAnimation(this, R.anim.splash_screen_animation)
        bgImg.startAnimation(anim)
        
        Handler(Looper.getMainLooper()).postDelayed(Runnable() {
            val intent = Intent(this@SplashScreenActivity, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }, delay)
    }
}