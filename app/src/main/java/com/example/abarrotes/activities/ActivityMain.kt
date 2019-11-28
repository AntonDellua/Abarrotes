package com.example.abarrotes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.abarrotes.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityMain : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val homeFragment = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val bottom = find<BottomNavigationView>(R.id.activity_main_bnv_navigation)
        bottom.setOnNavigationItemSelectedListener(this)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_content, FragmentMaps)
            .commit()*/
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val newFragmentReference = when (item.itemId) {
            R.id.action_home -> {
                //homeFragment
            }
            R.id.action_profile -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, ActivityProfile())
                    .commit()
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
}
