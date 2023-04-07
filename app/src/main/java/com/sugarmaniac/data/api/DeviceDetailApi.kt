package com.sugarmaniac.data.api

import com.sugarmaniac.data.ItemDetailData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DeviceDetailApi {

    @GET
    suspend fun getData(@Url string: String) : Response<ItemDetailData>
}