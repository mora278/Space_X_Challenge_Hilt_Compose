package com.example.spacexchallenge.data.dto

import com.google.gson.annotations.SerializedName

data class LinksDTO(
    @SerializedName("patch") val patch: PatchDTO?,
    @SerializedName("flickr") val flickr: FlickrDTO?,
    @SerializedName("article") val article: String?
)