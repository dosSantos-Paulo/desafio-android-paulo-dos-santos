package com.dossantos.desafioandroid.model.character

import com.dossantos.desafioandroid.model.comic.ComicsList

data class CharacterModel(
    val id: Int,
    val name: String,
    val thumbnail: ImageModel,
    val description: String,
    val comics: ComicsList
)
