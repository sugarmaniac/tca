package com.sugarmaniac.data

import com.fasterxml.jackson.annotation.JsonProperty

data class PageData(
    @JsonProperty("result")
    val result: Result
)