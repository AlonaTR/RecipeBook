package com.example.recipesproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Recipe::class], version = 1)
abstract class Recipes : RoomDatabase() {
    abstract fun getDao(): Dao
    companion object {
        fun getDb(context: Context): Recipes {
            return Room.databaseBuilder(
                context.applicationContext,
                Recipes::class.java,
                "recipe_db"
            ).build()
        }
    }

}