package com.example.spacexchallenge.data.api.retrofit

object RetrofitHelper {
    private const val OPTIONS = "options"
    private const val PAGE = "page"
    private const val LIMIT = "limit"
    private const val ITEMS_PER_PAGE = 5

    fun createBodyForPaging(page: Int): Map<String, Map<String, Any>> = mapOf(
        OPTIONS to mapOf(
            PAGE to page,
            LIMIT to ITEMS_PER_PAGE
        )
    )
}