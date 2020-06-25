package com.example.vocabulary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vocabulary.data.dao.WordDao
import com.example.vocabulary.data.model.Word

@Database(entities = [Word::class ],version = 2,exportSchema = false)
abstract class VocabularyDatabase:RoomDatabase() {

    companion object {
        private lateinit var INSTANCE:VocabularyDatabase
        fun getInstance(context:Context):VocabularyDatabase= Room.databaseBuilder(
            context,
            VocabularyDatabase::class.java,
             "vocabulary"

        )

            .createFromAsset("vocabulary.db")
            .allowMainThreadQueries()
            .build()

    }

    abstract fun dao():WordDao
}