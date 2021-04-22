package com.example.bellaonojie.fragments

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.bellaonojie.BellaApplication
import com.example.bellaonojie.R
import com.example.bellaonojie.database.UserDatabase
import com.example.bellaonojie.databinding.FragmentProfileBinding
import com.example.bellaonojie.viewmodel.UserViewModel
import com.example.bellaonojie.viewmodel.UserViewModelFactory

class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val binding: FragmentProfileBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        val application = requireNotNull(this.activity).application as BellaApplication

        val userViewModelFactory = UserViewModelFactory(application)
        val mUserViewModel = ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            userViewModel = mUserViewModel
            profileToolbar.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
        }

        return binding.root
    }
}