package com.example.spacexchallenge.domain.utils

import com.example.spacexchallenge.data.dto.LaunchDTO
import com.example.spacexchallenge.domain.models.LaunchInfo

object Mapper {
    fun LaunchDTO.toDomain(): LaunchInfo = LaunchInfo(
        id = this.id ?: "",
        name = this.name ?: "",
        date = this.date ?: "",
        details = this.details ?: "",
        urlPatch = this.urlPatch,
        urlArticle = this.urlArticle,
        urlPhotos = this.urlPhotos
    )
}
