package com.example.vocabulary.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.vocabulary.data.model.Word

@Dao
interface WordDao {

    @Query("SELECT*FROM book WHERE tip= :tip")
    fun getAllWords(tip:Int) :List<Word>

    @Query("SELECT*FROM book WHERE id=:id")
    fun getWordById(id:Int):Word

    @Query("SELECT *FROM book WHERE tip=:tip and word like :word")
    fun searchAnimalByName(tip:Int,word:String):List<Word>

    @Query("SELECT*FROM book WHERE type= :type")
    fun getWordsByType(type:String) :List<Word>

    @Query("SELECT*FROM book WHERE  isFavorite==1")
    fun getFavorites() :List<Word>

    @Update
    fun upDateWord(word:Word)
}