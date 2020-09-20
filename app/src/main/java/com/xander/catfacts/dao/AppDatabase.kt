package com.xander.catfacts.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xander.catfacts.model.CatFact

@Database(entities = [CatFact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catFactsDao(): CatFactsDao
}