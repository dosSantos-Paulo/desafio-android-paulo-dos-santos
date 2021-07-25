package com.dossantos.desafioandroid.model.character

data class CharacterDataContainer(
    val results: List<CharacterModel>,
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int
)
