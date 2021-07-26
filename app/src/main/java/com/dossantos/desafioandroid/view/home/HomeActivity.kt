package com.dossantos.desafioandroid.view.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dossantos.desafioandroid.R
import com.dossantos.desafioandroid.data.characters.CharacterRepository
import com.dossantos.desafioandroid.model.character.CharacterModel
import com.dossantos.desafioandroid.utils.KeyboardUtils
import com.dossantos.desafioandroid.viewmodel.character.CharacterViewModel
import com.google.android.material.textfield.TextInputLayout

class HomeActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView_home) }
    private val viewManager = GridLayoutManager(this, 2)
    private var characterList: MutableList<CharacterModel> = mutableListOf()
    private var homeAdapter = HomeAdapter(characterList)
    private lateinit var characterViewModel: CharacterViewModel
    private val progress by lazy { findViewById<ProgressBar>(R.id.progress_home) }
    private val textField by lazy { findViewById<TextInputLayout>(R.id.textField_findHero) }
    private val btnFind by lazy { findViewById<Button>(R.id.btn_find) }
    private val txtError by lazy { findViewById<TextView>(R.id.text_error) }
    private val imageError by lazy { findViewById<ImageView>(R.id.img_error) }
    private val btnNextPage by lazy {findViewById<Button>(R.id.btn_nextPage)}
    private val btnPrevPage by lazy {findViewById<Button>(R.id.btn_previousPage)}
    private var loading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupRecycler()
        setupViewModel()
        setupObservers()
        setClickListeners()
        onScrollListener()
    }

    fun onScrollListener(){
        var pastVisiblesItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0) {
                    hidePageButtons()
                }
                if (dy > 0) {
                    visibleItemCount = viewManager.childCount
                    totalItemCount = viewManager.itemCount
                    pastVisiblesItems = viewManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            showPageButtons()
                            loading = true
                        }
                    }
                }
            }
        })
    }

    fun hidePageButtons(){
        btnNextPage.animate().apply {
            alpha(0f)
            duration = 300
        }
        btnPrevPage.animate().apply {
            alpha(0f)
            duration = 300
        }
    }

    fun showPageButtons(){
        btnNextPage.animate().apply {
            alpha(1f)
            duration = 300
        }
        btnPrevPage.animate().apply {
            alpha(1f)
            duration = 300
        }
    }

    fun setupRecycler(){
        recyclerView.apply {
            layoutManager = viewManager
            adapter = homeAdapter
        }
    }

    fun setClickListeners(){
        btnFind.setOnClickListener {
            KeyboardUtils.hideKeyboard(this)
            continueObserver(textField.editText!!.text.toString(), true)
        }

        textField.editText?.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER){
                KeyboardUtils.hideKeyboard(this)
                continueObserver(textField.editText!!.text.toString(), true)
            }
            false
        }

        btnNextPage.setOnClickListener { continueObserver(null, true) }
        btnPrevPage.setOnClickListener { continueObserver(null, false) }
    }

    fun setupViewModel(){
        characterViewModel = ViewModelProvider(
            this,
            CharacterViewModel.Factory(CharacterRepository())
        ).get(CharacterViewModel::class.java)
    }

    fun setupObservers(){
        hidePageButtons()
        hideError()
        characterViewModel.getCharacter().observe(this) {
            progress.visibility = GONE
            if (it.isEmpty()){
                showError()
            }
            characterList.addAll(it)
            homeAdapter.notifyDataSetChanged()
        }
    }

    fun continueObserver(name: String?, pagePass : Boolean? = null) {
        hidePageButtons()
        characterList.clear()
        homeAdapter.notifyDataSetChanged()
        progress.visibility = VISIBLE
        hideError()
        characterViewModel.getCharacterByName(name, pagePass).observe(this) {
            progress.visibility = GONE
            if (it.isEmpty()){
                showError()
            }
            characterList.addAll(it)
            homeAdapter.notifyDataSetChanged()
        }
    }

    fun showError(){
        imageError.visibility = VISIBLE
        txtError.visibility = VISIBLE
    }

    fun hideError(){
        imageError.visibility = GONE
        txtError.visibility = GONE
    }

    companion object{
        val TAG = "HOME_ACTIVITY"
        fun getIntent(activity: Activity, bundle: String? = null){
            val intent = Intent(activity.applicationContext, HomeActivity::class.java)
            if (bundle != null){
                intent.putExtra(TAG, bundle)
            }
            activity.startActivity(intent)
        }
    }
}