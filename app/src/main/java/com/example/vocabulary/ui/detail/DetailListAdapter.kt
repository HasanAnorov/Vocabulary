package com.example.vocabulary.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vocabulary.R
import com.example.vocabulary.data.model.Word
import com.example.vocabulary.ui.word.WordClickListener
import kotlinx.android.synthetic.main.item_word.view.*

class DetailListAdapter(private val listener: WordClickListener):RecyclerView.Adapter<DetailListAdapter.DetailListViewHolder>() {

    var models:List<Word> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    inner class DetailListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun populateModel(model: Word){
            itemView.word.text=model.word
            itemView.setOnClickListener {
                listener.onWordItemClick(model.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailListViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_word,parent,false)
        return  DetailListViewHolder(view)
    }

    override fun getItemCount(): Int =models.size

    override fun onBindViewHolder(holder: DetailListViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
}