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
import android.widget.EditText
import android.widget.Toast
import com.example.abarrotes.utils.SESSION_ID_KEY
import com.example.abarrotes.utils.SHARED_PREFERENCES

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

            ParseUser
                .logInInBackground(
                    mUserName.text.toString(),
                    mPassword.text.toString()
                ) { parseUser, error ->
                    if (error == null) {
                        saveSessionToken(parseUser.sessionToken)
                        startActivity<ActivityMain>()
                    } else {
                        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        }

        find<TextView>(R.id.login_tv_create_account_action).setOnClickListener {
            startActivity<ActivityRegister>()
        }

        /*find<TextView>(R.id.login_tv_forgot_action).setOnClickListener {
            startActivity<ActivityMain>() // Activity Restore Password
        }*/
    }

    private fun saveSessionToken(sessionToken: String) {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SESSION_ID_KEY, sessionToken)
        editor.apply()
    }
}