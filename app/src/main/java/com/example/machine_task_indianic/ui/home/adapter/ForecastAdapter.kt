package com.example.machine_task_indianic.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machine_task_indianic.R
import com.example.machine_task_indianic.databinding.ForecastItemLayoutBinding
import com.example.machine_task_indianic.remote.model.ForecastData

class ForecastAdapter(private var forecastList: MutableList<ForecastData.Main>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ForecastViewHolder(
            ForecastItemLayoutBinding.inflate(
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

    class ForecastViewHolder(var binding: ForecastItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ForecastData.Main) {
          binding.textViewMinTemp.text = binding.root.context.resources.getString(R.string.min_temperature,item.main.tempMin.toString())
          binding.textViewMaxTemp.text = binding.root.context.resources.getString(R.string.max_temperature,item.main.tempMax.toString())
          binding.textViewHumidity.text = binding.root.context.resources.getString(R.string.humidity,item.main.humidity.toString())
          binding.textViewSeaLevel.text = binding.root.context.resources.getString(R.string.sea_level,item.main.seaLevel.toString())
          binding.textViewPressure.text = binding.root.context.resources.getString(R.string.pressure,item.main.pressure.toString())
        }
    }

}