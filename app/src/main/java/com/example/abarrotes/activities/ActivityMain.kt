package com.example.abarrotes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.abarrotes.R
import com.example.abarrotes.fragments.FragmentHome
import com.example.abarrotes.fragments.FragmentProfile
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.find

class ActivityMain : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val homeFragment = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottom = find<BottomNavigationView>(R.id.activity_main_bnv_navigation)
        bottom.setOnNavigationItemSelectedListener(this)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_content, FragmentHome())
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
            R.id.action_home -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, FragmentHome())
                    .commit()
            }
            R.id.action_profile -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, FragmentProfile())
                    .commit()
            }
            R.id.action_search -> {
                //Cambiar
                //homeFragment
            }
            else -> homeFragment
        }
        return true
    }
}
