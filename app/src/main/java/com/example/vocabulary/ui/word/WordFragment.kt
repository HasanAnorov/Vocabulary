package com.example.vocabulary.ui.word

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.vocabulary.R
import com.example.vocabulary.data.VocabularyDatabase
import com.example.vocabulary.data.dao.WordDao
import com.example.vocabulary.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_word.*

class WordFragment:Fragment(R.layout.fragment_word) {

    private val myAdapter=WordListAdapter()
    private lateinit var dao:WordDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter=myAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        dao=VocabularyDatabase.getInstance(requireContext()).dao()

        setData()
    }

    private fun setData(){
myAdapter.models=dao.getAllWords()
    }


}