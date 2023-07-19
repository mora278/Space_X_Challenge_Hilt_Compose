package com.example.spacexchallenge.ui.xml.utils;

import androidx.recyclerview.widget.DiffUtil
import com.example.spacexchallenge.domain.models.LaunchInfo

class LaunchInfoCallback : DiffUtil.ItemCallback<LaunchInfo>() {
    override fun areItemsTheSame(oldItem: LaunchInfo, newItem: LaunchInfo): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: LaunchInfo, newItem: LaunchInfo): Boolean =
        oldItem == newItem
}
