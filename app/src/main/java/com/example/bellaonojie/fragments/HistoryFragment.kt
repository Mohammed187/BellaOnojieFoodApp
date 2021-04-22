package com.example.bellaonojie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.bellaonojie.R
import com.example.bellaonojie.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            historyRecyclerView.apply {
                setHasFixedSize(true)
            }
            historyToolbar.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
        }

        return binding.root
    }
}