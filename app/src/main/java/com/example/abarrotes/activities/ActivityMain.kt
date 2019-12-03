package com.example.abarrotes.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.abarrotes.R
import com.example.abarrotes.fragments.FragmentHome
import com.example.abarrotes.fragments.FragmentMenu
import com.example.abarrotes.fragments.FragmentProfile
import com.example.abarrotes.fragments.storeFragment
import com.example.abarrotes.utils.SESSION_ID_KEY
import com.example.abarrotes.utils.SHARED_PREFERENCES
import com.google.android.gms.maps.model.Marker
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.ParseUser
import org.jetbrains.anko.find

class ActivityMain : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, OnMarkerClickListener,
    LogoutListener {

    private val homeFragment = FragmentHome()
    private val profileFragment = FragmentProfile()
    private val menuFragment = FragmentMenu()
    private val storeFragment = storeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottom = find<BottomNavigationView>(R.id.activity_main_bnv_navigation)
        bottom.setOnNavigationItemSelectedListener(this)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_content, homeFragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
            R.id.action_home -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, homeFragment)
                    .commit()
            }
            R.id.action_profile -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, profileFragment)
                    .commit()
            }
            R.id.action_menu -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, menuFragment)
                    .commit()
            }
            else -> homeFragment
        }
        return true
    }

    private fun changeFragment(newIndexFragment: Int) {

        val fragmentRef = when(newIndexFragment) {
            0 -> homeFragment
            1 -> storeFragment
            else -> homeFragment
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_content, fragmentRef)
            .commit()
    }

    override fun onMarkerClick(marker: Marker) {
        val bundle = Bundle()
        bundle.putString("name", marker.title)
        storeFragment.arguments = bundle
        changeFragment(1)
    }

    override fun logout() {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SESSION_ID_KEY, "")
        editor.apply()

        val user = ParseUser.getCurrentUser()

        ParseUser.logOut()

        val intent = Intent(this, ActivityLogin::class.java)
        startActivity(intent)
        finish()
    }


}

interface OnMarkerClickListener {
    fun onMarkerClick(marker: Marker)
    //fun passData(marker: Marker)
}

interface LogoutListener {
    fun logout()
}