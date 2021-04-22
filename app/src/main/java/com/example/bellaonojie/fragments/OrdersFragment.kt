package com.example.bellaonojie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.bellaonojie.R
import com.example.bellaonojie.cart.CartItemsAdapter
import com.example.bellaonojie.databinding.FragmentOrdersBinding


class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_orders, container, false)

        return binding.root
    }

}