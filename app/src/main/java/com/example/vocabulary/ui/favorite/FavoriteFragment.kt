package com.example.vocabulary.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.vocabulary.R
import com.example.vocabulary.data.VocabularyDatabase
import com.example.vocabulary.data.dao.WordDao
import com.example.vocabulary.ui.MainActivity
import com.example.vocabulary.ui.detail.DetailActivity
import com.example.vocabulary.ui.word.WordClickListener
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_word.*

class FavoriteFragment:Fragment(R.layout.fragment_favorite),WordClickListener {

    private lateinit var dao:WordDao
    private var adapter:FavoriteListAdapter = FavoriteListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteList.adapter=adapter
        favoriteList.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        favoriteList.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.HORIZONTAL))
        val tip =arguments?.getInt(MainActivity.TYPE_ID)?:1
        dao= VocabularyDatabase.getInstance(requireContext()).dao()

      onStart()
    }

    override fun onStart() {
        super.onStart()
setData()
    }

    private fun setData(){
        adapter.models=dao.getFavorites()
    }

    override fun onWordItemClick(id: Int) {
        val mIntent= Intent(requireContext(), DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.ANIMAL_ID,id)
        startActivity(mIntent)

    }
}