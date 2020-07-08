package com.example.vocabulary.ui.word

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vocabulary.R
import com.example.vocabulary.data.model.Word
import com.example.vocabulary.ui.MainActivity
import kotlinx.android.synthetic.main.item_word.view.*

class WordListAdapter(private val listener:WordClickListener):RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {



    inner class WordViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){



        fun populateUzbModel(model:Word){
            itemView.word.text=model.word
            itemView.setOnClickListener {
                listener.onWordItemClick(model.id)
            }

    }

    }

    var models:List<Word> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_word,parent,false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int =models.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
holder.populateUzbModel(models[position])

    }


}