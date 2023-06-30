package com.example.spacexchallenge.data.dto

import com.google.gson.annotations.SerializedName

data class FlickrDTO(
    @SerializedName("original") val original: List<String>
)