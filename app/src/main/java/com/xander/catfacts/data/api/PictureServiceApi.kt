package com.xander.catfacts.data.api

import com.xander.catfacts.data.model.response.PictureResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface PictureServiceApi {

    @GET("meow")
    fun getPicture() : Deferred<PictureResponse>

}