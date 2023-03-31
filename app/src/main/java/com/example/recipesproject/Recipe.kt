package com.example.recipesproject
import androidx.room.*
import java.io.Serializable

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "imageId")
    val imageId: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "ingridients")
    val ingridients: String,
    @ColumnInfo(name = "steps")
    val steps: String
) : Serializable

