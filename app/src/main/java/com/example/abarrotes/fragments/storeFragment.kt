package com.example.abarrotes.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.abarrotes.R
import org.jetbrains.anko.find

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.runOnUiThread

class storeFragment : Fragment() {

    private var markerName: String? = ""
    private lateinit var mStoreName: TextView
    private lateinit var mStoreDescription: TextView
    private lateinit var mStoreProduct: TextView

    private lateinit var sName: String
    private lateinit var sDescription: String
    private lateinit var sProduct: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store, container, false)
        markerName = arguments?.getString("name")
        mStoreName = view.find(R.id.store_name)
        mStoreDescription = view.find(R.id.store_description)
        mStoreProduct = view.find(R.id.store_product)
        //mStoreName.text = markerName

        doAsync {
            val query = ParseQuery.getQuery<ParseObject>("Tienditas")
            query.whereEqualTo("Name", markerName)
            query.findInBackground { obj, e ->
                runOnUiThread {
                    if (e == null) {
                        for (store in obj) {
                            sName = store.get("Name").toString()
                            sDescription = store.get("Description").toString()
                            sProduct = store.get("Product").toString()

                            mStoreName.text = sName
                            mStoreDescription.text = sDescription
                            mStoreProduct.text = sProduct
                        }
                    } else Log.e("error", e.toString())
                }
            }
        }


        return view
    }
}