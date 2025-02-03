package com.emperormoh.imagesearchapp.domain.usecase

import com.emperormoh.imagesearchapp.domain.repository.ImageRepository
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    operator fun invoke(q:String) = imageRepository.getImages(q)

}