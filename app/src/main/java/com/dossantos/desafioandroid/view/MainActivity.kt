package com.dossantos.desafioandroid.view

import android.os.Bundle
import android.view.View.GONE
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dossantos.desafioandroid.R
import com.dossantos.desafioandroid.data.characters.CharacterRepository
import com.dossantos.desafioandroid.data.comic.ComicRepository
import com.dossantos.desafioandroid.utils.StringUtils
import com.dossantos.desafioandroid.viewmodel.character.CharacterViewModel
import com.dossantos.desafioandroid.viewmodel.comic.ComicViewModel

class MainActivity : AppCompatActivity() {

    private val text by lazy { findViewById<TextView>(R.id.text) }
    private val progress by lazy { findViewById<ProgressBar>(R.id.progress) }

    private lateinit var comicViewModel: ComicViewModel
    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        comicViewModel = ViewModelProvider(
            this,
            ComicViewModel.Factory(ComicRepository())
        ).get(ComicViewModel::class.java)

        characterViewModel = ViewModelProvider(
            this,
            CharacterViewModel.Factory(CharacterRepository())
        ).get(CharacterViewModel::class.java)
    }

    private fun setupObservers() {
//        comicViewModel.getAllComics().observe(this) {
//            progress.visibility = GONE
//            text.text = it[0].title
//        }

        characterViewModel.getCharacter().observe(this) {
            comicViewModel.getMostValueComic(it[0].comics.items).observe(this) {
                progress.visibility = GONE
            }

        }
    }
}