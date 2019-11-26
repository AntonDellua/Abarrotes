package com.example.abarrotes

import android.app.Application
import com.parse.Parse

class AbarrotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this).applicationId("Kcsogp0q6eCS30a7Z84MXMzgtdcqoRPmNbnUmuvx")
                .clientKey("y92AE7wlclY2OoJKZ2tMrcw4KQ8KD8YRfMNQMKNt")
                .server("https://parseapi.back4app.com/")
                .build()
        )
    }
}