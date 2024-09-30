package com.example.wishlist

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val wishlist: MutableList<WishlistItem>) :
    RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    inner class WishlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.textViewItemName)
        val itemPrice: TextView = itemView.findViewById(R.id.textViewItemPrice)
        val itemUrl: TextView = itemView.findViewById(R.id.textViewItemUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wishlist_item, parent, false)
        return WishlistViewHolder(view)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val item = wishlist[position]
        holder.itemName.text = item.name
        holder.itemPrice.text = item.price
        holder.itemUrl.text = item.url

        // Make the URL clickable and open in a browser
        holder.itemUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return wishlist.size
    }
}
