package com.dossantos.desafioandroid.model.comic

import com.dossantos.desafioandroid.model.character.ImageModel

data class ComicModel(
    val id: Int,
    val title: String,
    val issueNumber: Double,
    val description: String,
    val prices: List<ComicPriceModel>,
    val dates: List<ComicDateModel>,
    val images: List<ImageModel>,
    val thumbnail: ImageModel,
    val pageCount: Int
)
