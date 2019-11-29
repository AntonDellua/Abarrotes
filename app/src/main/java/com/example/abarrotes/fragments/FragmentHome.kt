package com.example.abarrotes.fragments

import androidx.fragment.app.Fragment
import com.example.abarrotes.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_maps.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.uiThread

class FragmentHome : Fragment(), OnMapReadyCallback{
    private var mapFragment : SupportMapFragment? = null
    private var mMap: GoogleMap? = null
    private var mText = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.activity_maps, container, false)

        view.home_button_search.setOnClickListener {

            mText = view.home_til_search.text.toString()

            if (mText != "") {

                mMap?.clear() // clear markers

                doAsync {
                    val query = ParseQuery.getQuery<ParseObject>("Tienditas")
                    query.whereEqualTo("Product", mText)
                    query.findInBackground { obj, e ->
                        runOnUiThread {
                            if (e == null)
                                for(product in obj) {
                                    val pName = product.get("Name").toString()
                                    var pLat = product.get("Lat").toString()
                                    val dLat = java.lang.Double.parseDouble(pLat)
                                    val pLon = product.get("Lon").toString()
                                    val dLon = java.lang.Double.parseDouble(pLon)
                                    placeMarker(pName, dLat, dLon)
                                }
                            else
                                Log.e("error", e.toString())
                        }
                    }
                }
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapFragment = childFragmentManager?.findFragmentById(R.id.map) as? SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        placeMarker("Guadalajara", 20.6737777, -103.4054536)
    }

    private fun placeMarker(title: String, lat: Double, lon: Double) {
        if (mMap != null) {
            val marker = LatLng(lat, lon)
            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15f))
            mMap?.addMarker(MarkerOptions().title(title).position(marker))
        }
    }
}