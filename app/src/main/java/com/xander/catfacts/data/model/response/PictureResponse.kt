package com.xander.catfacts.data.model.response

import com.google.gson.annotations.SerializedName

class PictureResponse {
    @SerializedName("file") var picture: String = ""
}