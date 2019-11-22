package com.example.abarrotes.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.abarrotes.R
import com.example.abarrotes.Utils.SESSION_ID_KEY
import com.example.abarrotes.Utils.SHARED_PREFERENCES
import android.os.Handler

class ActivitySplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
            val sessionId = sharedPreferences.getString(SESSION_ID_KEY, "")

            val intent = if (sessionId == "") {
                Intent(this, ActivityLogin::class.java)
            } else {
                Intent(this, ActivityMain::class.java)
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)

            finish()

        }, 3680)
    }

}