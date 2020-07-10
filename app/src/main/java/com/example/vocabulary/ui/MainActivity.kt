package com.example.vocabulary.ui

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
import com.example.vocabulary.data.dao.WordDao
import com.example.vocabulary.data.model.Word
import com.example.vocabulary.ui.word.WordFragment

class MainActivity : AppCompatActivity() {

    companion object{
        const val TYPE_ID="typeId"
        const val UZB_ENG=1
        const val ENG_UZB=2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)



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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val searchItem=menu.findItem(R.id.menu_search)

      return super.onCreateOptionsMenu(menu)
    }




}