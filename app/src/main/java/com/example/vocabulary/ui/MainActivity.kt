package com.example.vocabulary.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.vocabulary.R
import com.example.vocabulary.data.VocabularyDatabase
import com.example.vocabulary.data.dao.WordDao
import com.example.vocabulary.data.model.Word
import com.example.vocabulary.ui.search.SearchAdapter
import com.example.vocabulary.ui.word.WordFragment
import com.example.vocabulary.ui.word.WordListAdapter
import kotlinx.android.synthetic.main.fragment_word.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val TYPE_ID="typeId"
        const val UZB_ENG=1
        const val ENG_UZB=2
    }

    private var chapterDatabase: VocabularyDatabase? = null
    private lateinit var searchView:SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        chapterDatabase = VocabularyDatabase.getInstance(this)!!


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle=ActionBarDrawerToggle(this, drawerLayout,toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val fragment=WordFragment()
        val bundle=Bundle()
        bundle.putInt(TYPE_ID, UZB_ENG)
        fragment.arguments=bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
        navView.setNavigationItemSelectedListener {
            val mFragment=WordFragment()
            val mBundle=Bundle()
            mBundle.getInt(TYPE_ID, UZB_ENG)
            mFragment.arguments=mBundle
            when(it.itemId){
                R.id.nav_uzb_eng ->{
                    mBundle.putInt(TYPE_ID, UZB_ENG)
                    mFragment.arguments=mBundle

                }

                R.id.nav_eng_uzb ->{
                    mBundle.putInt(TYPE_ID, ENG_UZB)
                    mFragment.arguments=mBundle

                }

                else -> return@setNavigationItemSelectedListener false
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,mFragment).commit()
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,WordFragment()).commit()

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//        val searchItem=menu.findItem(R.id.menu_search)
//        var searchView: SearchView
//        searchView = searchItem.actionView as SearchView
//        searchView.setSubmitButtonEnabled(true)
//        searchView.setQueryHint("Search here ...")
//
//         val myAdapter= SearchAdapter(this@MainActivity,
//            R.menu.main,
//         chapter)
//        recyclerView.adapter
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextChange(newText: String): Boolean {
//                return true
//            }
//            override fun onQueryTextSubmit(query: String): Boolean {
//                return true
//            }
//        })

//        val searchItem: MenuItem? = menu?.findItem(R.id.menu_search)
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView: SearchView? = searchItem?.actionView as SearchView
//
//        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        return super.onCreateOptionsMenu(menu)
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        val searchItem = menu!!.findItem(R.id.menu_search)
        searchView = searchItem.actionView as SearchView
        searchView.setSubmitButtonEnabled(true)
        searchView.setQueryHint("Search either - MindOrks, GetMeAnApp, BestContentApp, Hackerspace")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                getNamesFromDb(newText)
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                getNamesFromDb(query)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    private fun getNamesFromDb(searchText: String) {
        val searchTextQuery = "%$searchText%"
        chapterDatabase!!.dao().getChapterName(searchTextQuery)
            .observe(this, object :   Observer<List<Word>> {
                override fun onChanged(chapter: List<Word>?) {
                    if (chapter == null) {
                        return
                    }
                    val adapter = SearchAdapter(
                        this@MainActivity,
                        R.menu.main,
                        chapter
                    )
                    lvSearchResult.adapter = adapter
                }
            })
    }



}