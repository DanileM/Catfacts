package com.xander.catfacts.presentation.getfacts

import com.xander.catfacts.data.model.CatFact

interface FactsUseCase {
    suspend fun getCatFacts() : List<CatFact>
}