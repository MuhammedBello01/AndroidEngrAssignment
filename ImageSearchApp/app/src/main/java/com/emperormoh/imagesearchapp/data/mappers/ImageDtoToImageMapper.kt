package com.emperormoh.imagesearchapp.data.mappers

import com.emperormoh.imagesearchapp.data.model.remote.ImageDto
import com.emperormoh.imagesearchapp.domain.model.Image
import javax.inject.Inject

class ImageDtoToImageMapper@Inject constructor() : Mapper<ImageDto, Image> {
    override fun map(from: ImageDto): Image {
        return Image(
            id = from.id.toString(),
            imageUrl = from.largeImageURL
        )
    }
}