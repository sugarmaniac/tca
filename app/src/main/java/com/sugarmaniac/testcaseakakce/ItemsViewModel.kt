package com.sugarmaniac.testcaseakakce

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {

    //TODO: Add Item List

    val currentItem = MutableLiveData("") // TODO change this to data item
    val currentPage = MutableLiveData(1)


    fun fetchPage(){

    }

    fun selectItem(){

    }

    fun nextPage(){
        currentPage.value = currentPage.value?.plus(1)
    }

    fun previousPage(){
        currentPage.value = currentPage.value?.minus(1)
    }


}