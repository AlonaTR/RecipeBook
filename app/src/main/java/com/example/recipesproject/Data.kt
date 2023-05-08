package com.example.recipesproject

import androidx.room.*
import java.io.Serializable

@Entity
data class Data(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "imageId")
    val imageId: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "ingridients")
    val ingridients: String,
    @ColumnInfo(name = "steps")
    val steps: String,
    @ColumnInfo(name = "category")
    val category: String
) : Serializable
