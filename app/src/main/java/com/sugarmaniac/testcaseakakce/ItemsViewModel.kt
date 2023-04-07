package com.sugarmaniac.testcaseakakce

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sugarmaniac.data.HorizontalItem
import com.sugarmaniac.data.ItemDetailResult
import com.sugarmaniac.data.PageData
import com.sugarmaniac.data.VerticalItem
import com.sugarmaniac.data.api.ApiService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.net.URL

class ItemsViewModel : ViewModel() {

    private val apiService = ApiService()
    private val disposable = CompositeDisposable()
    private val firstLink = "https://mocki.io/v1/59906f35-d5d5-40f7-8d44-53fd26eb3a05"
    private val deviceLink = "https://mocki.io/v1/1a1fb542-22d1-4919-914a-750114879775?code="
    private val pageUrlList = mutableListOf(firstLink)

    val currentItem = MutableLiveData<ItemDetailResult>()
    val currentPage = MutableLiveData(1)
    val currentHorizontalItems = MutableLiveData<List<HorizontalItem>>(arrayListOf())
    val currentVerticalItems = MutableLiveData<List<VerticalItem>>(arrayListOf())
    var currentPageData : PageData? = null

    init {
        fetchPage(firstLink)
    }

    fun fetchDevice(code : String){
        viewModelScope.launch {
            val currentDeviceData = apiService.getDeviceDetail(deviceLink + code)
            currentItem.value = currentDeviceData.body()?.result
        }
    }

    fun fetchPage(url : String){
        viewModelScope.launch{
            currentPageData = apiService.getPageDetails(url).body()
            currentVerticalItems.value = currentPageData?.result?.products ?: emptyList()
            currentHorizontalItems.value = currentPageData?.result?.horizontalProducts ?: emptyList()
        }
    }

    fun nextPage(url : String){
        currentPage.value = currentPage.value?.plus(1)
        pageUrlList.add(currentPage.value!!, url)
        fetchPage(pageUrlList[currentPage.value!!])
    }

    fun previousPage(url : String){
        currentPage.value = currentPage.value?.minus(1)
        fetchPage(pageUrlList[currentPage.value!!])
    }


}