package com.emperormoh.imagesearchapp.data.model.remote

data class ImageResponse(
    val hits: List<ImageDto>,
    val total: Int,
    val totalHits: Int
)
