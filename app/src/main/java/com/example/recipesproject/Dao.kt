package com.example.recipesproject
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert
    fun insert(recipe: Data)
    @Query("SELECT * FROM Data")
    fun getAll(): Flow<List<Data>>
    //delete all
    @Query("DELETE FROM Data")
    fun deleteAll()
}