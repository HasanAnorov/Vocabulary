package com.example.vocabulary.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.vocabulary.R
import com.example.vocabulary.data.VocabularyDatabase
import com.example.vocabulary.data.dao.WordDao
import com.example.vocabulary.data.model.Word
import com.example.vocabulary.ui.MainActivity
import com.example.vocabulary.ui.word.WordClickListener
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_word.*
import kotlinx.android.synthetic.main.item_word.view.*

class DetailActivity : AppCompatActivity(),WordClickListener {
    companion object{
        const val ANIMAL_ID="animalId"
    }
    private var mAdapter=DetailListAdapter(this)
    private var animalId:Int=0
    private lateinit var currentWord:Word
    private lateinit var dao:WordDao
    private var menuItem:MenuItem?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Translation")

        setContentView(R.layout.activity_detail)
        dao=VocabularyDatabase.getInstance(this).dao()
        nestedRecyclerView?.adapter=mAdapter
        nestedRecyclerView?.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
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
        android.R.id.home ->finish()

        R.id.item_bookmark ->setFavorite()
    }
    return super.onOptionsItemSelected(item)
}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_detail,menu)
        menuItem=menu?.findItem(R.id.item_bookmark)
        setFavoriteIcon()
        return true
    }
    private  fun setFavorite(){
        if(currentWord.isFavorite==null){
            currentWord.isFavorite=1

        }

        else currentWord.isFavorite=1-currentWord.isFavorite!!
        currentWord.isFavorite=currentWord.isFavorite
        setFavoriteIcon()
        dao.upDateWord(currentWord)
    }

    private fun setFavoriteIcon(){
        if(currentWord.isFavorite==1 ){
            menuItem?.setIcon(R.drawable.ic_baseline_bookmark_24)
        }
        else {
            menuItem?.setIcon(R.drawable.ic_baseline_bookmark_border_24)
        }
    }

    override fun onWordItemClick(id: Int) {
        val mIntent= Intent(this, DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.ANIMAL_ID,id)
        startActivity(mIntent)
        finish()
    }
}