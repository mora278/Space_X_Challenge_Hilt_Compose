package com.example.spacexchallenge.data.dto

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class LaunchDTO(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("date_utc") val dateUtc: String?,
    @SerializedName("details") val details: String?,
    @SerializedName("links") private val links: LinksDTO?
) {
    val urlPatch: String
        get() = links?.patch?.small ?: links?.patch?.large ?: ""
    val urlPhotos: List<String>
        get() = links?.flickr?.original ?: emptyList()
    val urlArticle: String
        get() = links?.article ?: ""
    val date: String?
        get() = dateUtc?.formatDate()


    companion object {
        private fun String?.formatDate(): String? {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
            val outputFormat = SimpleDateFormat("dd MMM yyyy - HH:mm", Locale.getDefault())
            val date: Date? = this?.let {
                inputFormat.parse(it)
            }
            return date?.let { outputFormat.format(it) }
        }
    }
}