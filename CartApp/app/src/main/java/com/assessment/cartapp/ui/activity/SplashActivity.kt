package com.assessment.cartapp.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.assessment.cartapp.R

/*
 * @author Krish
 */

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        showSplash()
    }

    private fun showSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
            val nextScreen = Intent(this, LoginActivity::class.java)
            startActivity(nextScreen)
        }, 3000)
    }
}