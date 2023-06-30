package com.example.spacexchallenge.domain.usecases

import com.example.spacexchallenge.domain.services.URIHandlerService
import javax.inject.Inject

class GetUriHandlerUseCase @Inject constructor(
    private val uriHandlerService: URIHandlerService
) {
    operator fun invoke(uri: String) = uriHandlerService.openWebForURI(uri)
}