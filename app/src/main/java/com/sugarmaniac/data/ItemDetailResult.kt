package com.sugarmaniac.data

data class ItemDetailResult(
    val badge: String,
    val countOfPrices: Int,
    val freeShipping: Boolean,
    val imageUrl: String,
    val lastUpdate: String,
    val mkName: String,
    val price: Float,
    val productName: String,
    val rating: Double,
    val storageOptions: List<String>
)