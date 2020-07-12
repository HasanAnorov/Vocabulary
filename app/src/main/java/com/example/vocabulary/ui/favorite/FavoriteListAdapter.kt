package com.example.vocabulary.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vocabulary.R
import com.example.vocabulary.data.model.Word
import com.example.vocabulary.ui.word.WordClickListener
import kotlinx.android.synthetic.main.item_word.view.*

class FavoriteListAdapter(private val listener: WordClickListener):RecyclerView.Adapter<FavoriteListAdapter.FavoriteListViewHolder>() {

    var models:List<Word> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    inner class FavoriteListViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun populateFavorites(model:Word){
            itemView.word.text=model.word
            itemView.setOnClickListener {
                listener.onWordItemClick(model.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_word,parent,false)
        return FavoriteListViewHolder(view)
    }

    override fun getItemCount(): Int =models.size

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        holder.populateFavorites(models[position])
    }

}