package com.xander.catfacts.data.api

import com.xander.catfacts.data.model.response.FactResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface FactServiceApi {

    @GET("facts/random")
    fun getFact() : Deferred<FactResponse>

}