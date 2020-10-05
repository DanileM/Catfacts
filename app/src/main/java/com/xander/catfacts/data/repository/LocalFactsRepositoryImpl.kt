package com.xander.catfacts.data.repository

import com.xander.catfacts.data.db.dao.CatFactsDao
import com.xander.catfacts.data.model.CatFact
import com.xander.catfacts.domain.usecase.LocalFactsRepository

class LocalFactsRepositoryImpl(private val catFactsDao: CatFactsDao) : LocalFactsRepository {

    override suspend fun getCatFacts(): List<CatFact> {
        return catFactsDao.getLast().reversed()
    }

}