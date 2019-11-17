package com.example.abarrotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ActivityForgotPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)
        title = "Ayuda para iniciar sesión"

        val email = savedInstanceState?.get("email") ?: ""
    }

}