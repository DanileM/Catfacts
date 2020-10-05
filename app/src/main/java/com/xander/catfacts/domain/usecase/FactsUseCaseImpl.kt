package com.xander.catfacts.domain.usecase

import com.xander.catfacts.data.model.CatFact
import com.xander.catfacts.presentation.getfacts.FactsUseCase
import com.xander.catfacts.util.NetworkState

class FactsUseCaseImpl(
    private val factsRepository: FactsRepository,
    private val localFactsRepository: LocalFactsRepository,
    private val networkState: NetworkState
) : FactsUseCase {

    override suspend fun getCatFacts(): List<CatFact> {
        return if (networkState.isNetworkAvailable())
            factsRepository.getCatFacts()
        else localFactsRepository.getCatFacts()
    }
}