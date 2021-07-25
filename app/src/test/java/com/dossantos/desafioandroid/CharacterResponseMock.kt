package com.dossantos.desafioandroid

import com.dossantos.desafioandroid.model.character.CharacterDataContainer
import com.dossantos.desafioandroid.model.character.CharacterDataWrapper
import com.dossantos.desafioandroid.model.character.CharacterModel
import com.dossantos.desafioandroid.model.character.ImageModel
import com.dossantos.desafioandroid.model.comic.ComicSummary
import com.dossantos.desafioandroid.model.comic.ComicsList

class CharacterResponseMock {
    companion object{
        fun getCharacterModel(): CharacterDataWrapper {
            return CharacterDataWrapper(
                200,
                "ok",
                "mock",
                "mock",
                "mock",
                CharacterDataContainer(
                    listOf(
                        CharacterModel(
                            123,
                            "mock",
                            ImageModel("mock", "mock"),
                            "mock",
                            ComicsList(
                                1,
                                1,
                                "mock",
                                listOf(ComicSummary(
                                    "mock",
                                    "mock"
                                ))
                            )
                        )
                    ),
                    1,
                    20,
                    1,
                    1
                ),
                "mock"

            )
        }
    }
}