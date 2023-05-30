package com.example.recipesproject

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Data::class], version = 1)
abstract class Recipes : RoomDatabase() {
    abstract fun getDao(): Dao
    companion object {

        fun getDb(context: FragmentMainList): Recipes {
            return Room.databaseBuilder(
                context.requireContext(),
                Recipes::class.java,
                "recipedata_db"
            ).build()
        }
    }

}