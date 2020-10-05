package com.xander.catfacts.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xander.catfacts.data.db.dao.CatFactsDao
import com.xander.catfacts.data.model.CatFact

@Database(entities = [CatFact::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catFactsDao(): CatFactsDao
}