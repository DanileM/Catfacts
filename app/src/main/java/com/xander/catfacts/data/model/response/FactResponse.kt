package com.xander.catfacts.data.model.response

import com.google.gson.annotations.SerializedName

class FactResponse {
    @SerializedName("text") var fact: String = ""
}