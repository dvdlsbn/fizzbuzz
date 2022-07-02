package com.example.fizzbuzzletmecounttothatnumber.ui.adapter.paging

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.example.fizzbuzzletmecounttothatnumber.ui.fragment.NUMBER_OF_ITEMS_IN_PAGE

class MyPagingSource(private val fizzbuzzList: List<String>) : PagingSource<Int, String>() {

    override fun getRefreshKey(state: PagingState<Int, String>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        val page = params.key ?: 1
        val firstPageItemSize = 3 * NUMBER_OF_ITEMS_IN_PAGE
        val allOtherPagesItemSize = (page - 1) * NUMBER_OF_ITEMS_IN_PAGE

        val nextKey =
            if (fizzbuzzList.isEmpty()
                || firstPageItemSize + allOtherPagesItemSize + NUMBER_OF_ITEMS_IN_PAGE >= fizzbuzzList.count()
            ) {
                null
            } else {
                page.plus(1)
            }

        val fromIndex = (params.key?.times(params.loadSize) ?: 1) - 1
        val toIndex = when {
            fizzbuzzList.size < 3 * NUMBER_OF_ITEMS_IN_PAGE
                    || firstPageItemSize + allOtherPagesItemSize > fizzbuzzList.size
                    || firstPageItemSize + allOtherPagesItemSize + NUMBER_OF_ITEMS_IN_PAGE > fizzbuzzList.size
            -> {
                fizzbuzzList.lastIndex
            }
            params.key != null -> {
                firstPageItemSize + page * NUMBER_OF_ITEMS_IN_PAGE - 1
            }
            params.key == null -> {
                params.loadSize + NUMBER_OF_ITEMS_IN_PAGE - 1
            }
            else -> {
                fizzbuzzList.lastIndex
            }
        } + 1

        val fizzbuzzSubList = fizzbuzzList.subList(
            fromIndex = fromIndex,
            toIndex = toIndex
        )

        return Page(
            data = fizzbuzzSubList,
            prevKey = null,
            nextKey = nextKey

        )
    }
}