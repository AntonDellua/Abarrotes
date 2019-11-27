package com.example.abarrotes.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abarrotes.R
import com.example.abarrotes.utils.isValidEmail
import com.parse.ParseUser
import org.jetbrains.anko.find

class ActivityForgotPassword : AppCompatActivity() {

    private lateinit var mUserEmail: EditText
    private lateinit var mResetPass: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        mResetPass = find(R.id.restore_password_restore)
        mUserEmail = find(R.id.restore_tiet_email)

        mResetPass.setOnClickListener {
            if (!isValidEmail(mUserEmail.text.toString())) {
                Toast.makeText(this, "Email no válido", Toast.LENGTH_LONG).show()
            } else {

                ParseUser
                    .requestPasswordResetInBackground(
                        mUserEmail.text.toString()
                    ){
                        Toast.makeText(
                            this,
                            "Se le ha enviado un correo para actualizar su contraseña",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
        }
    }

}