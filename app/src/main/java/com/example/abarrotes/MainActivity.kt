package com.example.abarrotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), OnMapReadyCallback, BottomNavigationView.OnNavigationItemSelectedListener {

    // Fragment Declarations
    private val homeFragment /*: Fragment*/ = null

    override fun onCreate(savedInstanceState: Bundle?) {

        // Normal beginning, set content to main layout
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instance Google Maps
        val mMapFragment = SupportMapFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.map, mMapFragment)
            .commit()

        mMapFragment.getMapAsync(this)
    }

    // Initialize Google Maps
    override fun onMapReady(map: GoogleMap?) {
        map?.addMarker(
            MarkerOptions()
            .position(LatLng(0.0, 0.0))
            .title("Marker"))
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
