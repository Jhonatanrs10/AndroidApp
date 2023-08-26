package com.example.jhonatanrsanimes

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimeViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val textViewName = itemView.findViewById<TextView>(R.id.textViewName)
    private val textViewStatus = itemView.findViewById<TextView>(R.id.textViewStatus)
    private val textViewRelease = itemView.findViewById<TextView>(R.id.textViewRelease)

    fun bind(item: Anime) {
        //informações que aparecem no adapter tela inicial
        textViewName.text = item.name
        textViewStatus.text = item.status
        textViewRelease.text = item.release

        itemView.setOnClickListener{
            item.onClick?.invoke(item.id)
        }

    }



}