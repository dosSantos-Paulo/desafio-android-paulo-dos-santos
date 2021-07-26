package com.dossantos.desafioandroid.view.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
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
    private val progress by lazy { findViewById<ProgressBar>(R.id.progress_home) }
    private val textField by lazy { findViewById<TextInputLayout>(R.id.textField_findHero) }
    private val btnFind by lazy { findViewById<Button>(R.id.btn_find) }
    private val txtError by lazy { findViewById<TextView>(R.id.text_error) }
    private val imageError by lazy { findViewById<ImageView>(R.id.img_error) }
    private val btnNextPage by lazy {findViewById<Button>(R.id.btn_nextPage)}
    private val btnPrevPage by lazy {findViewById<Button>(R.id.btn_previousPage)}

    private val viewManager = GridLayoutManager(this, 2)
    private var characterList: MutableList<CharacterModel> = mutableListOf()
    private var homeAdapter = HomeAdapter(characterList)
    private lateinit var characterViewModel: CharacterViewModel
    private var loading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupRecycler()
        setupViewModel()
        getCharacters()
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
                            getNextPage()
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
            getCharacters(textField.editText!!.text.toString(), true)
        }

        textField.editText?.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER){
                KeyboardUtils.hideKeyboard(this)
                getCharacters(textField.editText!!.text.toString())
            }
            false
        }

        btnNextPage.setOnClickListener { getNextPage() }
        btnPrevPage.setOnClickListener { getPreviousPage() }
    }

    fun setupViewModel(){
        characterViewModel = ViewModelProvider(
            this,
            CharacterViewModel.Factory(CharacterRepository())
        ).get(CharacterViewModel::class.java)
    }

    fun getCharacters(name: String? = null, isNewSearch: Boolean? = null) {
        hidePageButtons()
        hideError()
        progress.visibility = VISIBLE
        characterViewModel.getCharacters(name, isNewSearch).observe(this) {
            progress.visibility = GONE
            characterList.clear()
            if (it.isNullOrEmpty()){
                showError()
            }else {
                characterList.addAll(it)
                homeAdapter.notifyDataSetChanged()
            }
        }
    }

    fun getNextPage(){
        progress.visibility = VISIBLE
        characterViewModel.getNextPage().observe(this) {
            progress.visibility = GONE
            if (it != null){
                characterList.addAll(it)
                homeAdapter.notifyDataSetChanged()
            }
        }
    }

    fun getPreviousPage(){
        hideError()
        progress.visibility = VISIBLE
        characterViewModel.getPreviousPage().observe(this) {
            progress.visibility = GONE
            if (!it.isNullOrEmpty()){
                hidePageButtons()
                characterList.clear()
                homeAdapter.notifyDataSetChanged()
                characterList.addAll(it)
                homeAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this, getString(R.string.no_previous_message),Toast.LENGTH_LONG).show()
            }
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