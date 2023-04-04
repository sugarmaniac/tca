package com.sugarmaniac.data

data class Product(
    val code: Int,
    val countOfPrices: Int,
    val dropRatio: Double,
    val followCount: Int,
    val imageUrl: String,
    val name: String,
    val price: Double
)