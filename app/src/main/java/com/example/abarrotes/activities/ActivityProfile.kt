package com.example.abarrotes.activities

import androidx.fragment.app.Fragment
import com.example.abarrotes.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Bundle

class ActivityProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

}