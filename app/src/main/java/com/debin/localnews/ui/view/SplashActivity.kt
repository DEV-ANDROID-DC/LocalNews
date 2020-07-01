package com.debin.localnews.ui.view

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.debin.localnews.R

class SplashActivity : AppCompatActivity()  {

    companion object {
        const val SPLASH_TIME: Long = 3000
        const val INTERVAL_TIME: Long = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        object : CountDownTimer(
            SPLASH_TIME,
            INTERVAL_TIME
        ) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                val intent = Intent(this@SplashActivity, NewsListActivity::class.java)
                startActivity(intent)
                // Override default transition animation with slide-in-out animation
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                finish()
            }
        }.start()

    }
}