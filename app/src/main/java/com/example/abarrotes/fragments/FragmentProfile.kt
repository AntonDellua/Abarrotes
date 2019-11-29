package com.example.abarrotes.fragments

import androidx.fragment.app.Fragment
import com.example.abarrotes.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Bundle
import com.parse.ParseUser
import kotlinx.android.synthetic.main.fragment_profile.view.*

class FragmentProfile : Fragment() {
    private var mUsername = ""
    private var mEmail = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        val user = ParseUser.getCurrentUser()
        mUsername = user.username
        mEmail = user.email

        view.name_text.text = mUsername
        view.email_text.text = mEmail

        return view
    }
}
