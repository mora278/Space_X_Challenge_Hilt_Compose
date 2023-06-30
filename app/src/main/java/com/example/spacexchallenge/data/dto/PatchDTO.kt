package com.example.spacexchallenge.data.dto

import com.google.gson.annotations.SerializedName

data class PatchDTO(
    @SerializedName("small") val small: String?,
    @SerializedName("large") val large: String?
)