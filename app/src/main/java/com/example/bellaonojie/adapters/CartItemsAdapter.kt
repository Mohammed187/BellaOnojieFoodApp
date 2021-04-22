package com.example.bellaonojie.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.bellaonojie.databinding.CartItemBinding
import com.example.bellaonojie.models.Cart
import com.example.bellaonojie.viewmodel.CartViewModel

class CartItemsAdapter(
    val onItemSwipeListener: OnItemSwipeListener,
    private val viewModel: CartViewModel
) :
    ListAdapter<Cart, CartItemsAdapter.CartViewHolder>(CartCallback) {

    class CartViewHolder(private var binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartModel: Cart, viewModel: CartViewModel) {
            binding.cart = cartModel

            binding.viewModel = viewModel

            binding.executePendingBindings()
        }
    }

    fun getCartItemAt(position: Int): Cart {
        return getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = CartItemBinding.inflate(LayoutInflater.from(parent.context))
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartModel = getItem(position)
        holder.bind(cartModel, viewModel)
    }
}

object CartCallback : DiffUtil.ItemCallback<Cart>() {
    override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem.id == newItem.id
    }
}

interface OnItemSwipeListener {
    fun onItemSwipe(cart: Cart)
}

class CartItemClickListener(val clickListener: (cartKey: Cart) -> Unit) {
    fun onClick(cartModel: Cart) = clickListener(cartModel)
}


