package com.xander.catfacts.data.db.dao

import androidx.room.*
import com.xander.catfacts.data.model.CatFact

@Dao
interface CatFactsDao {

    @Query("SELECT * FROM CatFacts ORDER BY id DESC LIMIT 10")
    fun getLast(): List<CatFact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(facts: List<CatFact>)
}