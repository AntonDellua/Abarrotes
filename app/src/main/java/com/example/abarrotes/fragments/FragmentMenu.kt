package com.example.abarrotes.fragments

import androidx.fragment.app.Fragment
import com.example.abarrotes.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Bundle
import android.widget.Button
import com.parse.ParseUser
import kotlinx.android.synthetic.main.fragment_menu.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class FragmentMenu : Fragment() {
    // Variables
    private lateinit var mLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_menu, container, false)

        return view
    }
}
