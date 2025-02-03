package com.emperormoh.imagesearchapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.emperormoh.imagesearchapp.data.local.ImageDao
import com.emperormoh.imagesearchapp.data.local.RemoteKeyDao
import com.emperormoh.imagesearchapp.data.mappers.ImageDtoToImageMapper
import com.emperormoh.imagesearchapp.data.mappers.ImageEntityToImageMapper
import com.emperormoh.imagesearchapp.data.pagingsource.ImagePagingSource
import com.emperormoh.imagesearchapp.data.pagingsource.ImageRemoteMediator
import com.emperormoh.imagesearchapp.data.remote.ApiService
import com.emperormoh.imagesearchapp.domain.model.Image
import com.emperormoh.imagesearchapp.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: ImageDtoToImageMapper,
    private val imageDao: ImageDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val imageEntityToImageMapper: ImageEntityToImageMapper
): ImageRepository {
    override fun getImages(q: String): Pager<Int, Image> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                ImagePagingSource(
                    apiService = apiService,
                    q = q,
                    mapper = mapper
                )
            }
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getRemoteMediatorImages(q: String): Flow<PagingData<Image>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = true,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                imageDao.getImages(q)
            },
            remoteMediator = ImageRemoteMediator(
                query = q,
                imageDao = imageDao,
                remoteKeyDao = remoteKeyDao,
                apiService = apiService
            )
        ).flow
            .map {
                it.map {
                    imageEntityToImageMapper.map(it)
                }
            }
    }

}