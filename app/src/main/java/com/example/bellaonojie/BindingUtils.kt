package com.example.bellaonojie

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bellaonojie.cart.CartItemsAdapter
import com.example.bellaonojie.models.Item
import com.example.bellaonojie.menu.MenuItemsAdapter
import com.example.bellaonojie.models.Cart
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("menuList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Item>?) {
    val adapter = recyclerView.adapter as MenuItemsAdapter
    adapter.submitList(data)
}

@BindingAdapter("cartList")
fun loadCartItems(recyclerView: RecyclerView, data: List<Cart>?) {
    val adapter = recyclerView.adapter as CartItemsAdapter
    adapter.submitList(data)
}

@BindingAdapter("loadImage")
fun bindImage(imageView: CircleImageView, src: String?) {
    Glide.with(imageView).load(src).into(imageView)
}