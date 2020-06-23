package com.example.vocabulary.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "book")
data class Word(
    @PrimaryKey val id :Int,

    @ColumnInfo(name="type")
    val type:Int,

    @ColumnInfo(name="nameUzb")
    val nameUzb:String,

    @ColumnInfo(name="nameEng")
    val nameEng:String


    )