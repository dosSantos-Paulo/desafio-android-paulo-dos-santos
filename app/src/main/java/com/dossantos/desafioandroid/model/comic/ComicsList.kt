package com.dossantos.desafioandroid.model.comic

data class ComicsList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ComicSummary
)