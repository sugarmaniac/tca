package com.sugarmaniac.data.api

import com.sugarmaniac.data.ItemDetailData
import com.sugarmaniac.data.PageData
import io.reactivex.Single
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val BASE_DEVICE_URL = "https://mocki.io/v1/"

    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_DEVICE_URL)
        .build()
        .create(DeviceDetailApi::class.java)

    suspend fun getDeviceDetail(string: String) : Response<ItemDetailData>{
        return api.getData(string)
    }

    private val api1 = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_DEVICE_URL)
        .build()
        .create(PageApi::class.java)

    suspend fun getPageDetails(string: String) : Response<PageData>{
        return api1.getData(string)
    }

}