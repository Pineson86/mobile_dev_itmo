package com.example.shop.data

import com.example.shop.data.model.CartItem
import com.example.shop.data.model.Order
import com.example.shop.data.model.Product
import com.example.shop.data.model.User

object Repository {
    // Mock products
    val products = listOf(
        Product(1, "Смартфон", "Современный смартфон с отличной камерой", 299.99),
        Product(2, "Планшет", "Планшет для работы и развлечений", 399.99)
    )

    // Mock authenticated user
    var currentUser: User? = null

    // Shopping cart
    var cartItems = mutableListOf<CartItem>()

    // User orders
    var userOrders = mutableListOf<Order>()
    
    fun addToCart(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            val index = cartItems.indexOf(existingItem)
            cartItems[index] = existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            cartItems.add(CartItem(product, 1))
        }
    }

    fun removeFromCart(productId: Int) {
        cartItems.removeAll { it.product.id == productId }
    }

    fun getCartTotal(): Double {
        return cartItems.sumOf { it.product.price * it.quantity }
    }

    fun placeOrder(): String {
        if (cartItems.isEmpty() || currentUser == null) return ""

        val orderId = "ORDER-${System.currentTimeMillis()}"
        val order = Order(
            id = orderId,
            userId = currentUser!!.name,
            products = cartItems.toList(),
            totalPrice = getCartTotal(),
            date = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(java.util.Date())
        )

        userOrders.add(order)
        cartItems.clear()

        return orderId
    }
}