package com.xander.catfacts.api

import com.xander.catfacts.model.response.FactResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface FactServiceApi {

    @GET("facts/random")
    fun getFact() : Deferred<FactResponse>

}