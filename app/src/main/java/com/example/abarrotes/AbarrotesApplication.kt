package com.example.abarrotes

import android.app.Application
import com.parse.Parse
//import javax.swing.UIManager.put
import com.parse.ParseInstallation
import com.example.abarrotes.R.*

class AbarrotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(string.back4app_app_id))
                // if defined
                .clientKey(getString(string.back4app_client_key))
                .server(getString(string.back4app_server_url))
                .build()
        )
        // Parse Push Notification
        val installation = ParseInstallation.getCurrentInstallation()
        installation.put("GCMSenderId", "602907222268")
        installation.saveInBackground()

    }
}