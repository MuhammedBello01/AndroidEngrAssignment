package com.emperormoh.imagesearchapp.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.emperormoh.imagesearchapp.data.mappers.ImageDtoToImageMapper
import com.emperormoh.imagesearchapp.data.mappers.mapAll
import com.emperormoh.imagesearchapp.data.remote.ApiService
import com.emperormoh.imagesearchapp.domain.model.Image
import kotlinx.coroutines.delay

class ImagePagingSource(
    private val apiService: ApiService,
    private val q: String,
    private val mapper: ImageDtoToImageMapper
): PagingSource<Int, Image>() {
    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        return try {
            delay(3000)
            val images = apiService.getImages(q = q, page = page)
            LoadResult.Page(
                data = mapper.mapAll(images.hits),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (images.hits.size < pageSize) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}