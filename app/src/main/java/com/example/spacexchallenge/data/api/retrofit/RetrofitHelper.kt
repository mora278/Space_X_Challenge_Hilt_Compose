package com.example.spacexchallenge.data.api.retrofit

object RetrofitHelper {
    const val OPTIONS = "options"
    const val PAGE = "page"
    private const val LIMIT = "limit"
    private const val ITEMS_PER_PAGE = 5

    fun createBodyForPaging(page: Int): TestOptionsBody =
        TestOptionsBody(
            options = TestBody(
                page = page,
                limit = ITEMS_PER_PAGE
            )
        )
}

data class TestBody(
    val page: Int,
    val limit: Int
)
data class TestOptionsBody(
    val options: TestBody
)