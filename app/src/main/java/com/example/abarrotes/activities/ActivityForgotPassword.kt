package com.example.abarrotes.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.abarrotes.R

class ActivityForgotPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)
        title = "Ayuda para iniciar sesión"

        val email = savedInstanceState?.get("email") ?: ""
    }

}