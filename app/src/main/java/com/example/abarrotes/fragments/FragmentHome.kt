package com.example.abarrotes.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.abarrotes.R
import com.example.abarrotes.activities.ActivityMain
import com.example.abarrotes.activities.OnMarkerClickListener
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.parse.Parse.getApplicationContext
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_maps.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.runOnUiThread


class FragmentHome : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private var mapFragment: SupportMapFragment? = null
    private var mMap: GoogleMap? = null
    private var mText = ""
    private lateinit var listener: OnMarkerClickListener
    private var currentLocation: Location? = null
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private lateinit var mLocationButton:Button
    val REQUEST_CODE_LOCATION = 1234
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ActivityMain
        } catch (e: ClassCastException) {
            error { e }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity!!)
        //buscar la ultima ubicacion del dispositivo
        fetchLocation()
        var view = inflater.inflate(R.layout.activity_maps, container, false)
        // boton para ir a la ubicacion
        mLocationButton = view.findViewById(R.id.home_button_loc)
        mLocationButton.setOnClickListener {
            fetchLocation()
        }
        view.home_button_search.setOnClickListener {

            mText = view.home_til_search.text.toString()

            if (mText != "") {

                mMap?.clear() // clear markers

                doAsync {
                    val query = ParseQuery.getQuery<ParseObject>("Tienditas")
                    query.whereEqualTo("Product", mText)
                    query.findInBackground { obj, e ->
                        runOnUiThread {
                            if (e == null) {
                                for (product in obj) {
                                    val pName = product.get("Name").toString()
                                    var pLat = product.get("Lat").toString()
                                    val dLat = java.lang.Double.parseDouble(pLat)
                                    val pLon = product.get("Lon").toString()
                                    val dLon = java.lang.Double.parseDouble(pLon)
                                    placeMarker(pName, dLat, dLon)
                                }
                            } else
                                Log.e("error", e.toString())
                        }
                    }
                }
            }
        }
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
        val markerOptions = MarkerOptions().position(latLng).title("Ubicacion actual")
        mMap = googleMap

        mMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        mMap?.addMarker(markerOptions)
        //placeMarker("Guadalajara", 20.6737777, -103.4054536)
        mMap?.setOnMarkerClickListener(this)
    }

    private fun placeMarker(title: String, lat: Double, lon: Double) {
        if (mMap != null) {
            val marker = LatLng(lat, lon)
            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15f))
            mMap?.addMarker(MarkerOptions().title(title).position(marker).snippet("La Gran Plaza"))

        }
    }

    /** Called when the user clicks a marker. */
    override fun onMarkerClick(marker: Marker): Boolean {
        // Implementar cambio de fragmento a traves de interface y ActivityMain
        // En fragmento llamado, pasar informacion de marcador clickeado
        // Segun info de marcador, ir a parse y obtener info de tiendita
        // Con info de tiendita, mostrarla dinamicamente en xml de fragmento
        listener.onMarkerClick(marker)
        //listener.passData(marker)
        return true
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // pedir permiso al usuario
        if (requestCode == REQUEST_CODE_LOCATION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // permiso concedido
            return

        } else {
            // permiso aun no concedido
            AlertDialog.Builder(activity!!)
                .setCancelable(false)
                .setTitle("¡Permiso necesario!")
                .setMessage("Para mostrar la ubicación actual es necesario el permiso.\n¿Desea conceder el permiso?")
                .setPositiveButton("Aceptar") { _, _ ->
                    // pedir permiso al usuario
                    requestLocationPermission()
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()
        }
    }

    private fun fetchLocation() {
        // request permisos de ubicacion
        if (ActivityCompat.checkSelfPermission(
                context!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context!!, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION)
            return
        }
        // buscar la ubicacion del dispositivo
        val task: Task<Location> = fusedLocationProviderClient!!.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location
                mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment?
                mapFragment?.getMapAsync(this)
            }
        }
    }
}
