package com.example.spacexchallenge.data.dto

import com.google.gson.annotations.SerializedName

data class PostResponseDTO(
    @SerializedName("docs") val data: List<LaunchDTO>
)