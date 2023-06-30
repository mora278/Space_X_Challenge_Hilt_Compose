package com.example.spacexchallenge.data.network

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.spacexchallenge.domain.services.URIHandlerService

class URIHandlerServiceImpl(private val context: Context): URIHandlerService {
    override fun openWebForURI(uri: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}