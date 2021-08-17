package com.example.machine_task_indianic.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machine_task_indianic.ui.`interface`.OnItemClickListener
import com.example.machine_task_indianic.databinding.LocationHistoryItemLayoutBinding
import com.example.machine_task_indianic.remote.model.LocationHistoryData

class LocationHistoryAdapter(
    private var forecastList: MutableList<LocationHistoryData>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<LocationHistoryAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ForecastViewHolder(
            LocationHistoryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

  inner class ForecastViewHolder(var binding: LocationHistoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }

        fun bind(item: LocationHistoryData) {
            binding.textViewLatitude.text = item.lat
            binding.textViewLongitude.text = item.lng
            binding.textViewAddress.text = item.address
        }
    }

}