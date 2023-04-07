package com.sugarmaniac.data.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sugarmaniac.data.PageData
import retrofit2.Response

class PaginSource(
    val backend: ApiService,
) : PagingSource<Int, Response<PageData>>() {

    private var pageData : PageData? = null
    private var page = 1
    private val firstLink = "https://mocki.io/v1/59906f35-d5d5-40f7-8d44-53fd26eb3a05"

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Response<PageData>> {
        try {
            val response = if (pageData?.result?.nextUrl != null){
                backend.getPageDetails(pageData!!.result.nextUrl!!)
            } else {
                backend.getPageDetails(firstLink)
            }
            pageData = response.body()
            page += 1
            return LoadResult.Page(
                data = listOf(response),
                prevKey = null, // Only paging forward.
                nextKey = page
            )
        } catch (e: Exception) {
            Log.d("", e.toString())
            return LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Response<PageData>>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}