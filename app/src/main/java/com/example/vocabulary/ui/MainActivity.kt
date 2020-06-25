package com.example.vocabulary.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.vocabulary.R
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

            when(it.itemId){
                R.id.nav_uzb_eng ->{
                    bundle.putInt(TYPE_ID, UZB_ENG)
                    fragment.arguments=bundle

                }

                R.id.nav_eng_uzb ->{
                    bundle.putInt(TYPE_ID, ENG_UZB)
                    fragment.arguments=bundle

                }

                else -> return@setNavigationItemSelectedListener false
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
            return@setNavigationItemSelectedListener true
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,WordFragment()).commit()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        val searchItem=menu.findItem(R.id.menuSearch)

        return true
    }


}