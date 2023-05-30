package com.example.cpaweb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.cpaweb.helpers.AuthManager

class Splash : AppCompatActivity() {
    private val splashScreenTimeout : Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AuthManager.init(this)
        val token = AuthManager.getAuthToken()


        Handler().postDelayed({
            if(token != null){
                startActivity(Intent(this, CommunityHome::class.java))
            }else{
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, splashScreenTimeout)
    }
}