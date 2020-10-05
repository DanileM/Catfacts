package com.xander.catfacts.data.repository

import com.xander.catfacts.data.api.FactServiceApi
import com.xander.catfacts.data.api.PictureServiceApi
import com.xander.catfacts.data.db.dao.CatFactsDao
import com.xander.catfacts.data.model.CatFact
import com.xander.catfacts.domain.usecase.FactsRepository

class FactsRepositoryImpl(
    private val pictureServiceApi: PictureServiceApi,
    private val factServiceApi: FactServiceApi,
    private val catFactsDao: CatFactsDao
) : FactsRepository {

    override suspend fun getCatFacts(): List<CatFact> {
        val catFactsList = arrayListOf<CatFact>()

        while (catFactsList.size != 10) {
            val catFact = CatFact()
            try {
                val responsePicture = pictureServiceApi.getPicture().await()
                catFact.picture = responsePicture.picture

                val responseFact = factServiceApi.getFact().await()
                catFact.fact = responseFact.fact

                catFactsList.add(catFact)

                if (catFactsList.size == 10)
                    catFactsDao.add(catFactsList)

            } catch (e: Exception) {
                //handle error
            }
        }

        return catFactsList
    }
}