package com.example.vocabulary.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.vocabulary.R
import com.example.vocabulary.data.model.Word

class SearchAdapter (context: Context, val layout: Int,val chapter:List<Word>):ArrayAdapter<Word>(context, layout, chapter) {
    override fun getCount(): Int {
        return chapter.size
    }
    override fun getItem(position: Int): Word? {
        return chapter.get(position)
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var retView: View
        val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (convertView == null) {
            retView = vi.inflate(layout, null)
        } else {
            retView = convertView
        }
        var chapterItem = getItem(position)
        val chapterName = retView.findViewById(R.id.menu_search) as TextView
        chapterName.text = chapterItem!!.translation
        return retView
    }
}