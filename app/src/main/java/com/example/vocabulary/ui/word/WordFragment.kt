package com.example.vocabulary.ui.word

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.vocabulary.R
import com.example.vocabulary.data.VocabularyDatabase
import com.example.vocabulary.data.dao.WordDao
import com.example.vocabulary.ui.MainActivity
import com.example.vocabulary.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_word.*

class WordFragment:Fragment(R.layout.fragment_word),WordClickListener {

    private val myAdapter=WordListAdapter(this)
    private lateinit var dao:WordDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter=myAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        recyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.HORIZONTAL))
        val tip =arguments?.getInt(MainActivity.TYPE_ID)?:1
        dao=VocabularyDatabase.getInstance(requireContext()).dao()

        setData(tip)
    }

    private fun setData(tip:Int){
        myAdapter.models=dao.getAllWords(tip)
    }

    override fun onWordItemClick(id:Int) {
        val mIntent=Intent(requireContext(),DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.ANIMAL_ID,id)
        startActivity(mIntent)
        
    }


}