package com.sugarmaniac.data.api

import com.sugarmaniac.data.ItemDetailData
import com.sugarmaniac.data.PageData
import io.reactivex.Single
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PageApi {

    @GET
    suspend fun getData(@Url string: String) : Response<PageData>
}