package com.example.abarrotes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.abarrotes.R
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import com.parse.ParseUser
import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import com.example.abarrotes.utils.SESSION_ID_KEY
import com.example.abarrotes.utils.SHARED_PREFERENCES
import com.example.abarrotes.utils.isEmptyInput
import com.example.abarrotes.utils.isValidEmail

class ActivityLogin : AppCompatActivity() {

    // Variables
    private lateinit var mLogin: Button
    private lateinit var mUserName: EditText
    private lateinit var mUserEmail: EditText
    private lateinit var mPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        mLogin = find(R.id.login_btn_login)
        mUserName = find(R.id.login_tiet_username)
        mUserEmail = find(R.id.login_tiet_email)
        mPassword = find(R.id.login_tiet_password)

        mLogin.setOnClickListener {
            if (isEmptyInput(mUserName.text.toString()) || isEmptyInput(mPassword.text.toString())) {
                Toast.makeText(this, "Revisar campos vacios", Toast.LENGTH_LONG).show()
            }
            else if (!isValidEmail(mUserEmail.text.toString())) {
                Toast.makeText(this, "Email no válido", Toast.LENGTH_LONG).show()
            } else {
                ParseUser
                    .logInInBackground(
                        mUserName.text.toString(),
                        mPassword.text.toString()
                    ) { parseUser, error ->
                        if (error == null && parseUser?.get("emailVerified") == true) {
                            saveSessionToken(parseUser.sessionToken)
                            val intent = Intent(this, ActivityMain::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                            startActivity(intent)
                            finish()
                        }else if (parseUser?.get("emailVerified") == false) {
                            Toast.makeText(this, "Su correo electrónico no esta validado", Toast.LENGTH_LONG)
                                .show()
                        }
                        else {
                            Toast.makeText(this, "Error al iniciar session", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            }
        }

        find<TextView>(R.id.login_tv_create_account_action).setOnClickListener {
            startActivity<ActivityRegister>()
        }

        find<TextView>(R.id.login_tv_forgot_action).setOnClickListener {
            startActivity<ActivityForgotPassword>()
        }
    }

    private fun saveSessionToken(sessionToken: String) {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SESSION_ID_KEY, sessionToken)
        editor.apply()
    }
}
