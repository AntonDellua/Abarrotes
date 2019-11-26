package com.example.abarrotes.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abarrotes.R
import com.parse.ParseUser
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class ActivityForgotPassword : AppCompatActivity() {

    private lateinit var mUserEmail: EditText
    private lateinit var mResetPass: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        mResetPass = find(R.id.restore_password_restore)
        mUserEmail = find(R.id.restore_tiet_email)

        mResetPass.setOnClickListener {
            Toast.makeText(this, "Se le ha enviado un correo para actualizar su contraseña", Toast.LENGTH_LONG).show()
        }
    }

}