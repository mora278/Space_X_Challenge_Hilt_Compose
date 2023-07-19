package com.example.spacexchallenge.ui.xml.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.spacexchallenge.databinding.ItemListLaunchBasicInfoBinding
import com.example.spacexchallenge.domain.models.LaunchInfo
import com.example.spacexchallenge.ui.xml.utils.LaunchInfoCallback

class LaunchInfoBasicAdapter(
    private val onClick: (launchId: String) -> Unit
) : ListAdapter<LaunchInfo, LaunchInfoBasicViewHolder>(LaunchInfoCallback()) {
    private lateinit var binding: ItemListLaunchBasicInfoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchInfoBasicViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemListLaunchBasicInfoBinding.inflate(layoutInflater, parent, false)
        return LaunchInfoBasicViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: LaunchInfoBasicViewHolder, position: Int) {
        getItem(position)?.let { launchInfo ->
            holder.render(launchInfo)
            holder.itemView.setOnClickListener { onClick(launchInfo.id) }
        }
    }
}