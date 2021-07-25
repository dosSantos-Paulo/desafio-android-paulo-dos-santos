package com.dossantos.desafioandroid.model.character

data class CharacterModel (
    val id: Int,
    val name: String,
    val thumbnail: ImageModel,
    val description: String
)
