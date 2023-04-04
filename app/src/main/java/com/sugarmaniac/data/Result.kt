package com.sugarmaniac.data

data class Result(
    val horizontalProducts: List<HorizontalProduct>,
    val nextUrl: String?,
    val products: List<Product>
)