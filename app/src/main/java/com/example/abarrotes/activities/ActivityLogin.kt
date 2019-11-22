package com.example.abarrotes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.abarrotes.R

import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
//import com.parse.ParseUser

//import iteso.mx.itesogram.utils.SESSION_ID_KEY
//import iteso.mx.itesogram.utils.SHARED_PREFERENCES

class ActivityLogin : AppCompatActivity() {

    // Variables
    private lateinit var mLogin: Button
    private lateinit var mUserName: EditText
    private lateinit var mPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

    /*
    private fun saveSessionToken(sessionToken: String) {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SESSION_ID_KEY, sessionToken)
        editor.apply()
    }*/

}