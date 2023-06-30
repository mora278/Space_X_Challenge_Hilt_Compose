package com.example.spacexchallenge.domain.models

data class LaunchInfo(
    val id: String,
    val name: String,
    val date: String,
    val details: String,
    val urlPatch: String,
    val urlArticle: String,
    val urlPhotos: List<String>
)