package com.sugarmaniac.testcaseakakce

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.google.gson.Gson
import com.sugarmaniac.data.HorizontalItem
import com.sugarmaniac.data.ItemDetailResult
import com.sugarmaniac.data.PageData
import com.sugarmaniac.data.VerticalItem
import com.sugarmaniac.data.api.ApiService
import com.sugarmaniac.data.api.PaginSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result
import java.net.URL

class ItemsViewModel : ViewModel() {

    private val apiService = ApiService()
    private val firstLink = "https://mocki.io/v1/59906f35-d5d5-40f7-8d44-53fd26eb3a05"
    private val deviceLink = "https://mocki.io/v1/1a1fb542-22d1-4919-914a-750114879775?code="
    private var pageData : PageData? = null
    private var currentPage = 0

    private var loading = false

    val currentItem = MutableLiveData<ItemDetailResult>()
    val currentHorizontalItems = MutableLiveData<List<HorizontalItem>>(arrayListOf())
    val currentVerticalItems = MutableLiveData<MutableList<VerticalItem>>(arrayListOf())

    init {
        fetchPage(firstLink, 0)
    }

    fun fetchDevice(code : String){
        viewModelScope.launch {
            val currentDeviceData = apiService.getDeviceDetail(deviceLink + code)
            currentItem.value = currentDeviceData.body()?.result
        }
    }

    fun fetchPage(url : String, position : Int){
        if (loading)
            return
        loading = true
        viewModelScope.launch{
            pageData = apiService.getPageDetails(url).body()

            if (position == 0){
                currentVerticalItems.value = pageData?.result?.products?.toMutableList() ?: mutableListOf()
                currentHorizontalItems.value = pageData?.result?.horizontalProducts ?: emptyList()
            } else {
                pageData?.result?.products?.toMutableList()
                    ?.let { currentVerticalItems.value?.addAll(it) }
                currentVerticalItems.value = currentVerticalItems.value
            }
            loading = false
        }
    }

    fun loadMore(){
        currentPage += 1
        if (pageData?.result?.nextUrl != null){
//            currentPage += 1
            pageData?.result?.nextUrl?.let { fetchPage(it, currentPage) }
        }
    }

}