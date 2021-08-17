package com.example.machine_task_indianic.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.machine_task_indianic.R
import com.example.machine_task_indianic.remote.model.ForecastData
import com.example.machine_task_indianic.remote.model.Parameter
import com.example.machine_task_indianic.databinding.FragmentForecastBinding
import com.example.machine_task_indianic.ui.base.BaseFragment
import com.example.machine_task_indianic.ui.home.adapter.ForecastAdapter
import com.example.machine_task_indianic.ui.viewModel.HomeViewModel
import com.example.machine_task_indianic.utils.Status
import com.example.machine_task_indianic.utils.hide
import com.example.machine_task_indianic.utils.show
import com.example.machine_task_indianic.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : BaseFragment<FragmentForecastBinding>() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var adapter: ForecastAdapter? = null
    private var forecastList: MutableList<ForecastData.Main> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initRecycler()
        callWeather()
    }

    private fun initRecycler() {
        adapter = ForecastAdapter(forecastList)
        mViewBinding.recyclerViewForecast.adapter = adapter
    }

    private fun callWeather() {
        val parameter = Parameter()
        parameter.lat = homeViewModel.latitude.toString()
        parameter.lon = homeViewModel.longitude.toString()
        homeViewModel.getForecast(parameter)
    }

    private fun initObserver() {
        homeViewModel.forecastLivaData.observe(requireActivity(), {
            when (it.status) {
                Status.SUCCESS -> {
                    mViewBinding.progress.hide()
                    it.data?.let { data ->
                        if (data.list.isNotEmpty()) {
                            forecastList.addAll(data.list)
                            adapter?.notifyDataSetChanged()
                        }
                    }
                }
                Status.LOADING -> {
                    mViewBinding.progress.show()

                }
                Status.ERROR -> {
                    mViewBinding.progress.hide()
                    requireContext().showMessage(resources.getString(R.string.something_went_wrong))
                }
            }
        })
    }

    override fun getViewBinding(): FragmentForecastBinding =
        FragmentForecastBinding.inflate(layoutInflater)
}