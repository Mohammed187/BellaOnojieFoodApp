package com.example.bellaonojie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bellaonojie.BellaApplication
import com.example.bellaonojie.R
import com.example.bellaonojie.cart.CartItemsAdapter
import com.example.bellaonojie.viewmodel.CartViewModel
import com.example.bellaonojie.viewmodel.CartViewModelFactory
import com.example.bellaonojie.cart.OnItemSwipeListener
import com.example.bellaonojie.databinding.FragmentCartBinding
import com.example.bellaonojie.models.Cart

class CartFragment : Fragment(), OnItemSwipeListener {

    private lateinit var cartItemsAdapter: CartItemsAdapter
    private lateinit var _cartViewModel: CartViewModel
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)

        val application = requireNotNull(this.activity).application as BellaApplication

        val cartModelFactory = CartViewModelFactory(application)

        _cartViewModel =
                ViewModelProvider(this, cartModelFactory).get(CartViewModel::class.java)

        cartItemsAdapter = CartItemsAdapter(this, _cartViewModel)

        cartItemsAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                checkEmpty()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                checkEmpty()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                checkEmpty()
            }

            fun checkEmpty() {
                binding.emptyView.visibility =
                        (if (cartItemsAdapter.itemCount == 0) View.VISIBLE else View.GONE)
            }
        })

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            cartViewModel = _cartViewModel
            cartRecyclerView.apply {
                setHasFixedSize(true)
                adapter = cartItemsAdapter
                itemAnimator = DefaultItemAnimator()
            }
            cartToolbar.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
        }

        _cartViewModel.allCartItems.observe(viewLifecycleOwner, { items ->
            items?.let { cartItemsAdapter.submitList(it) }
            Toast.makeText(requireContext(), "Total Items : ${items.size}", Toast.LENGTH_LONG)
                    .show()
        })

        val itemTouchHelper =
                ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                    override fun onMove(
                            recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder
                    ): Boolean {
                        TODO("Not yet implemented")
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.adapterPosition
                        val cart = cartItemsAdapter.getCartItemAt(position)
                        cartItemsAdapter.onItemSwipeListener.onItemSwipe(cart)
                        binding.cartRecyclerView.adapter!!.notifyItemRemoved(position)
                    }

                })

        itemTouchHelper.attachToRecyclerView(binding.cartRecyclerView)

        return binding.root
    }

    override fun onItemSwipe(cart: Cart) {
        _cartViewModel.deleteCartItem(cart)
        Toast.makeText(requireContext(), "Item ${cart.name} was deleted", Toast.LENGTH_SHORT).show()
    }

}