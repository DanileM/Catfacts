package com.xander.catfacts.api

import com.xander.catfacts.model.response.PictureResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface PictureServiceApi {

    @GET("meow")
    fun getPicture() : Deferred<PictureResponse>

}