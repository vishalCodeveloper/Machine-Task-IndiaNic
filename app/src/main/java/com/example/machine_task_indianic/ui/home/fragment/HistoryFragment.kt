package com.example.machine_task_indianic.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.machine_task_indianic.R
import com.example.machine_task_indianic.ui.`interface`.OnItemClickListener
import com.example.machine_task_indianic.databinding.FragmentHistoryBinding
import com.example.machine_task_indianic.remote.model.LocationHistoryData
import com.example.machine_task_indianic.ui.base.BaseFragment
import com.example.machine_task_indianic.ui.home.adapter.LocationHistoryAdapter
import com.example.machine_task_indianic.ui.viewModel.DatabaseViewModel
import com.example.machine_task_indianic.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    private val databaseViewModel: DatabaseViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    private var adapter: LocationHistoryAdapter? = null
    private var historyList: MutableList<LocationHistoryData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        getData()
    }

    private fun getData() {
        databaseViewModel.getAllWeatherData().observe(viewLifecycleOwner) { list ->
            list?.let {
               if(it.isNotEmpty()){
                   historyList.clear()
                   historyList.addAll(it)
                   adapter?.notifyDataSetChanged()
               }
            }
        }
    }

    private fun initRecycler() {
        adapter = LocationHistoryAdapter(historyList,object : OnItemClickListener{
            override fun onItemClick(position: Int) {
                homeViewModel.latitude = historyList[position].lat.toDouble()
                homeViewModel.longitude = historyList[position].lat.toDouble()
                homeViewModel.address = getAddress(historyList[position].lat.toDouble(),historyList[position].lat.toDouble())
                homeViewModel.isDataAdded = true
                findNavController().navigate(R.id.action_history_fragment_to_weather_info_fragment)
            }

        })
        mViewBinding.recyclerViewHistory.adapter = adapter
    }

    override fun getViewBinding(): FragmentHistoryBinding =
        FragmentHistoryBinding.inflate(layoutInflater)
}