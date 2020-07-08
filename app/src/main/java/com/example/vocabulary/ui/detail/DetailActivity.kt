package com.example.vocabulary.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.vocabulary.R
import com.example.vocabulary.data.VocabularyDatabase
import com.example.vocabulary.data.dao.WordDao
import com.example.vocabulary.data.model.Word
import com.example.vocabulary.ui.MainActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_word.*
import kotlinx.android.synthetic.main.item_word.view.*

class DetailActivity : AppCompatActivity() {
    companion object{
        const val ANIMAL_ID="animalId"
    }
    private var mAdapter=DetailListAdapter()
    private var animalId:Int=0
    private lateinit var currentWord:Word
    private lateinit var dao:WordDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nestedRecyclerView.adapter=mAdapter
        nestedRecyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Translation")

        setContentView(R.layout.activity_detail)
        dao=VocabularyDatabase.getInstance(this).dao()
        animalId = intent.getIntExtra(ANIMAL_ID, 0)
        currentWord = dao.getWordById(animalId)
        word.text = currentWord.word
        type.text=currentWord.type
        tranlation.text=currentWord.translation

        setData(currentWord.type)
    }

    private fun setData(type:String){
        mAdapter.models=dao.getWordsByType(type)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home ->
                finish()
        }
        return super.onOptionsItemSelected(item)
    }
}