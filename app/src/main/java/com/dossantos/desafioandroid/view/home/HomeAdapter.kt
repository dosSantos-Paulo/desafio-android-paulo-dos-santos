package com.dossantos.desafioandroid.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dossantos.desafioandroid.R
import com.dossantos.desafioandroid.model.character.CharacterModel
import com.squareup.picasso.Picasso

class HomeAdapter(private val list: MutableList<CharacterModel>): RecyclerView.Adapter<HomeAdapter.homeViewHolder>() {

    class homeViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val image: ImageView = view.findViewById(R.id.item_image)
        private val name: TextView = view.findViewById(R.id.item_text)

        fun bind (character: CharacterModel) {
            val thumbnail = "${character.thumbnail.path}/portrait_uncanny.${character.thumbnail.extension}"
            Picasso.get().load(thumbnail).into(image)
            name.text = character.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character_home, parent, false)
        return homeViewHolder(view)
    }

    override fun onBindViewHolder(holder: homeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

}