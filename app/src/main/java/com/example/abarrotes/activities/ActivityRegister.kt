package com.example.abarrotes.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.abarrotes.R
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class ActivityRegister : AppCompatActivity() {

    // Variables
    private lateinit var mRegister: Button
    private lateinit var mUserName: EditText
    private lateinit var mUserEmail: EditText
    private lateinit var mPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        var selectedButton = ""
        mRegister = find(R.id.register_btn_register)
        mUserName = find(R.id.register_tiet_name)
        mUserEmail = find(R.id.register_tiet_email)
        mPassword = find(R.id.register_tiet_password)
        val radioGroup = findViewById<RadioGroup>(R.id.register_rg_buttons)
        radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.register_rb_mas -> selectedButton = "Male"
                R.id.register_rb_fem -> selectedButton = "Female"
                R.id.register_rb_other -> selectedButton = "Other"
            }
        }

        mRegister.setOnClickListener {
            val user = ParseUser()
            user.username = mUserName.text.toString()
            user.email = mUserEmail.text.toString()
            user.setPassword(mPassword.text.toString())
            user.put("gender", selectedButton)

            user.signUpInBackground { error ->
                if (error == null) {
                    Toast.makeText(this, "Registro completado", Toast.LENGTH_LONG).show()
                    startActivity<ActivityLogin>()
                } else {
                    Toast.makeText(this, "Error en registro", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}