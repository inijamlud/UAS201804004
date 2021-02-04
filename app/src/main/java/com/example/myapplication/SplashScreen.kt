package com.app.UAS201804004

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.constraintlayout.widget.ConstraintLayout

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val layout :ConstraintLayout = findViewById(R.id.layout)

        layout.setBackgroundResource(R.drawable.splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },2000)
    }
}