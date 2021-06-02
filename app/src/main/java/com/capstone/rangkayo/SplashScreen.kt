package com.capstone.rangkayo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.capstone.rangkayo.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    companion object {
        const val INTERVAL = 2000
    }

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val anim = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        val animScale = AnimationUtils.loadAnimation(applicationContext, R.anim.scale)
        binding.image.startAnimation(anim)
        binding.image.startAnimation(animScale)


        Handler(mainLooper).postDelayed({
            startActivity(Intent(this@SplashScreen, WelcomeActivity::class.java))
            finish()
        }, INTERVAL.toLong())
    }

}
