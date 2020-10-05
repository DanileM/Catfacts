package com.xander.catfacts.domain.usecase

import com.xander.catfacts.data.model.CatFact

interface LocalFactsRepository {
    suspend fun getCatFacts() : List<CatFact>
}