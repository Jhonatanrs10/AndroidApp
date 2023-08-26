package com.example.jhonatanrsanimes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AnimeAdapter: RecyclerView.Adapter<AnimeViewHolder>() {

    private var items = listOf<Anime>()

    fun updateItems(newItems: List<Anime>){
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(items[position])
    }
}