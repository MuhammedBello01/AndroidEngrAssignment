package com.emperormoh.imagesearchapp.data.mappers

import com.emperormoh.imagesearchapp.data.model.local.ImageEntity
import com.emperormoh.imagesearchapp.data.model.remote.ImageDto

class ImageDtoToImageEntityMapper( private val query: String): Mapper<ImageDto, ImageEntity> {
    override fun map(from: ImageDto): ImageEntity {
        return ImageEntity(
            id = from.id.toString(),
            imageUrl = from.largeImageURL,
            query = query
        )
    }
}