package com.example.spacexchallenge.ui.xml.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.spacexchallenge.R
import com.example.spacexchallenge.domain.models.UIState
import com.example.spacexchallenge.databinding.ItemListPhotoBinding
import com.squareup.picasso.Picasso

object BindingAdapterUtils {
    fun <T> View.showLoadingGroup(uiState: UIState<T>) {
        visibility = if (uiState is UIState.Loading) View.VISIBLE else View.GONE
    }

    fun <T> View.showContentGroup(uiState: UIState<T>) {
        visibility = if (uiState is UIState.Success) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:urlImage")
    fun urlImage(
        imageView: ImageView,
        url: String?
    ) {
        if (url?.isNotBlank() == true) {
            Picasso.get().load(url).error(R.drawable.image_not_supported_24).into(imageView)
        } else {
            imageView.setImageResource(R.drawable.image_not_supported_24)
        }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["isTextValueAvailable", "textValueTarget", "defaultTextValue"],
        requireAll = true
    )
    fun isTextValueAvailable(
        textView: TextView,
        isTextValueAvailable: Boolean,
        value: String,
        defaultValue: String
    ) {
        textView.text = if (isTextValueAvailable) value else defaultValue
    }


    @JvmStatic
    @BindingAdapter(
        value = ["urlPhotos", "layoutId"],
        requireAll = true
    )
    fun mediaItems(container: ViewGroup, urlPhotos: List<String>?, layoutId: Int) {
        urlPhotos?.forEach { urlPhoto ->
            val layoutInflater = LayoutInflater.from(container.context)
            val binding: ItemListPhotoBinding =
                DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
            binding.ulrPhoto = urlPhoto
            container.addView(binding.root)
        }

    }
}