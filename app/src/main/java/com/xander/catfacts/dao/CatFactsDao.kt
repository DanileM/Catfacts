package com.xander.catfacts.dao

import androidx.room.*
import com.xander.catfacts.model.CatFact

@Dao
interface CatFactsDao {

    @Query("SELECT * FROM CatFacts ORDER BY id DESC LIMIT 10")
    fun getLast(): List<CatFact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(facts: List<CatFact>)
}