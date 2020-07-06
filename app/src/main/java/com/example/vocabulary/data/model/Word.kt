package com.example.vocabulary.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "book")
data class Word(
    @PrimaryKey val id :Int,

    @ColumnInfo(name="type")
    val type:String,

    @ColumnInfo(name="word")
    val word:String,

    @ColumnInfo(name="translation")
    val translation:String,

    @ColumnInfo(name="tip")
    val tip:Int?


    )