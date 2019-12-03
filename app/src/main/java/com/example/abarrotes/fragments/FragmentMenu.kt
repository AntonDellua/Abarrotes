package com.example.abarrotes.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.abarrotes.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.abarrotes.activities.LogoutListener
import com.parse.ParseUser
import kotlinx.android.synthetic.main.fragment_menu.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class FragmentMenu : Fragment(), View.OnClickListener {
    // Variables
    private lateinit var listener: LogoutListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as LogoutListener
        } catch (error: ClassCastException) {
            Log.e("FragmentProfile", "The activity must implement LogoutListener, $error")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_menu, container, false)

        view.findViewById<Button>(R.id.menu_logout).setOnClickListener(this)

        return view
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.menu_logout -> {
                listener.logout()
            }
        }
    }
}
