package com.xander.catfacts.model.response

import com.google.gson.annotations.SerializedName

class FactResponse {
    @SerializedName("text") var fact: String = ""
}