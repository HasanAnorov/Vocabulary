package com.example.vocabulary.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.vocabulary.data.model.Word

@Dao
interface WordDao {

    @Query("SELECT*FROM book WHERE tip=tip")
    fun getAllWords() :List<Word>

}