package com.example.recipesproject
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert
    fun insert(recipe: Recipe)
    @Query("SELECT * FROM Recipe")
    fun getAll(): Flow<List<Recipe>>
    //delete all
    @Query("DELETE FROM Recipe")
    fun deleteAll()
}