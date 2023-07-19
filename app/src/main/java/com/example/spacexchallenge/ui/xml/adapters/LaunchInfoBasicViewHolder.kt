package com.example.spacexchallenge.ui.xml.adapters

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexchallenge.databinding.ItemListLaunchBasicInfoBinding
import com.example.spacexchallenge.domain.models.LaunchInfo

class LaunchInfoBasicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding: ItemListLaunchBasicInfoBinding? = DataBindingUtil.bind(view)

    fun render(launchInfo: LaunchInfo) {
        binding?.launchInfo = launchInfo
    }
}