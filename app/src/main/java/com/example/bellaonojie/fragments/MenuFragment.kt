package com.example.bellaonojie.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.bellaonojie.BellaApplication
import com.example.bellaonojie.R
import com.example.bellaonojie.databinding.FragmentMenuBinding
import com.example.bellaonojie.menu.MenuItemListener
import com.example.bellaonojie.menu.MenuItemsAdapter
import com.example.bellaonojie.viewmodel.MenuViewModel
import com.google.android.material.tabs.TabLayout

class MenuFragment : Fragment() {

    private lateinit var menuAdapter: MenuItemsAdapter
    private lateinit var _viewModel: MenuViewModel
    private lateinit var binding: FragmentMenuBinding
    private lateinit var application: BellaApplication

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        application = requireNotNull(this.activity).application as BellaApplication

        menuAdapter = MenuItemsAdapter(MenuItemListener { item ->
            _viewModel.onMenuItemClicked(item)
        })

        loadMenu("Foods")

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = _viewModel
            menuRecyclerView.apply {
                setHasFixedSize(true)
                adapter = menuAdapter
                itemAnimator = DefaultItemAnimator()
            }
        }

        binding.categoryTabLayout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                loadMenu(tab?.text.toString())
                Log.d("Tab", "onTabSelected: ${tab?.text} ")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Nothing to be done
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                loadMenu(tab?.text.toString())
                Log.d("Tab", "onTabReselected: ${tab?.text} ")
            }

        })

        _viewModel.navigateToItemDetail.observe(viewLifecycleOwner, { item ->

            Log.d("Item", "navigateToItemDetail: ${item?.name}")

            item?.let {
                this.findNavController().navigate(
                        MenuFragmentDirections.actionMenuFragmentToItemDetails(item)
                )

//                _viewModel.onItemDetailNavigated()
            }
        })

        binding.apply {
            topAppBar.setNavigationOnClickListener {
                binding.drawerLayout.open()
            }

            navigationView.setNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                binding.drawerLayout.close()

                when (menuItem.itemId) {
                    R.id.ic_profile -> findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToProfileFragment())
                    R.id.ic_orders -> findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToOrdersFragment2())
                }
                true
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    fun loadMenu(category: String) {

        _viewModel = MenuViewModel(application, category)

        _viewModel.itemsByCat.observe(viewLifecycleOwner, { items ->
            items.let {
                menuAdapter.submitList(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.cartFragment -> {
                _viewModel.navigateToCart.observe(viewLifecycleOwner, {
                    this.findNavController().navigate(
                            MenuFragmentDirections.actionMenuFragmentToCartFragment()
                    )
                })
                _viewModel.onCartNavigated()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}