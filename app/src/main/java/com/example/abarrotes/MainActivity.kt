package com.example.abarrotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    // Fragment Declarations
    private val homeFragment = null

    override fun onCreate(savedInstanceState: Bundle?) {

        // Normal beginning, set content to main layout
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Nav Menu Functionality
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val newFragmentReference = when (item.itemId) {
            R.id.action_home -> {
                //homeFragment
            }
            R.id.action_profile -> {
                //Cambiar
                //homeFragment
            }
            R.id.action_search -> {
                //Cambiar
                //homeFragment
            }
            else -> homeFragment
        }
        //changeFragment(newFragmentReference)
        return true
    }

    /*
    // Fragment Changer implemented with Nav Menu Functionality
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main_fl_main_content, fragment)
            .commit()
    }
    */
}
