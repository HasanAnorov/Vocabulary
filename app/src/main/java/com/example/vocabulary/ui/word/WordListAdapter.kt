package com.example.vocabulary.ui.word

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vocabulary.R
import com.example.vocabulary.data.model.Word
import com.example.vocabulary.ui.MainActivity
import kotlinx.android.synthetic.main.item_word.view.*

class WordListAdapter():RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {



    inner class WordViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun populateEngModel(model:Word){
            itemView.word.text=model.translation
            when(model.type){
                1-> itemView.type.text="Noun"
                2->itemView.type.text="Adjectivw"
                3-> itemView.type.text="Number"
                4->itemView.type.text="Verb"
                5-> itemView.type.text="Adverb"
            }

        }

        fun populateUzbModel(model:Word){
            itemView.word.text=model.word
            when(model.type){
                1-> itemView.type.text="Ot"
                2->itemView.type.text="Sifat"
                3-> itemView.type.text="Son"
                4->itemView.type.text="Fe'l"
                5-> itemView.type.text="Ravish"
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