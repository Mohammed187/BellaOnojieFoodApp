package com.example.bellaonojie.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bellaonojie.BellaApplication
import com.example.bellaonojie.R
import com.example.bellaonojie.viewmodel.CartViewModel
import com.example.bellaonojie.viewmodel.CartViewModelFactory
import com.example.bellaonojie.database.MenuDatabase
import com.example.bellaonojie.databinding.ItemDetailsFragmentBinding
import com.example.bellaonojie.viewmodel.ItemDetailsViewModel
import com.example.bellaonojie.viewmodel.ItemDetailsViewModelFactory

class ItemDetailsFragment : Fragment() {

    private val args by navArgs<ItemDetailsFragmentArgs>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding: ItemDetailsFragmentBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_details_fragment, container, false)

        val application = requireNotNull(this.activity).application as BellaApplication

        val viewModelFactory = ItemDetailsViewModelFactory(args.currentItem, application)
        val itemDetailsViewModel =
                ViewModelProvider(this, viewModelFactory).get(ItemDetailsViewModel::class.java)

        val cartModelFactory = CartViewModelFactory(application)
        val mCartViewModel =
                ViewModelProvider(this, cartModelFactory).get(CartViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = itemDetailsViewModel
            cartViewModel = mCartViewModel
            toolbar.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
        }

        itemDetailsViewModel.navigateToMenuItems.observe(viewLifecycleOwner, {
            if (it == true) {
                this.findNavController().navigate(
                        ItemDetailsFragmentDirections.actionItemDetailsToMenuFragment()
                )
                itemDetailsViewModel.doneNavigating()
            }
        })

        mCartViewModel.cartAlert.observe(viewLifecycleOwner, {
            if (it == true) {
                Log.d("Cart", "Cart Alert : $it")
                Toast.makeText(requireContext(), "Item Added to Cart.", Toast.LENGTH_SHORT).show()
                mCartViewModel.itemAddedDone()
            }
        })

        return binding.root
    }

}