package com.example.wishlist

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val wishlistItems = mutableListOf<WishlistItem>()
    private lateinit var adapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views using findViewById
        val itemNameEditText = findViewById<EditText>(R.id.editTextItemName)
        val itemPriceEditText = findViewById<EditText>(R.id.editTextItemPrice)
        val itemUrlEditText = findViewById<EditText>(R.id.editTextItemUrl)
        val addButton = findViewById<Button>(R.id.btnAddItem)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewWishlist)

        // Initialize RecyclerView
        adapter = WishlistAdapter(wishlistItems)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add item to wishlist when button is clicked
        addButton.setOnClickListener {
            val itemName = itemNameEditText.text.toString()
            val itemPrice = itemPriceEditText.text.toString()
            val itemUrl = itemUrlEditText.text.toString()

            if (validateInput(itemName, itemPrice, itemUrl)) {
                val wishlistItem = WishlistItem(itemName, itemPrice, itemUrl)
                wishlistItems.add(wishlistItem)
                adapter.notifyItemInserted(wishlistItems.size - 1)

                // Clear the input fields
                itemNameEditText.text.clear()
                itemPriceEditText.text.clear()
                itemUrlEditText.text.clear()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(name: String, price: String, url: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(price) || TextUtils.isEmpty(url))
    }
}
