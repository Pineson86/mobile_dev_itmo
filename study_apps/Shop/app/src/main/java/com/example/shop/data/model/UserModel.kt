package com.example.shop.data.model

data class User(
    val name: String,
    val email: String
)

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String = ""
)

data class CartItem(
    val product: Product,
    val quantity: Int = 1
)

data class Order(
    val id: String,
    val userId: String,
    val products: List<CartItem>,
    val totalPrice: Double,
    val date: String
)