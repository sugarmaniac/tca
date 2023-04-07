package com.sugarmaniac.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Result(
    @JsonProperty("horizontalProducts")
    val horizontalProducts: List<HorizontalItem>,
    @JsonProperty("nextUrl")
    val nextUrl: String?,
    @JsonProperty("products")
    val products: List<VerticalItem>
)