package com.example.machine_task_indianic.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.machine_task_indianic.R
import com.example.machine_task_indianic.databinding.FragmentCurrentWeatherBinding
import com.example.machine_task_indianic.remote.model.LocationHistoryData
import com.example.machine_task_indianic.remote.model.Parameter
import com.example.machine_task_indianic.remote.model.WeatherData
import com.example.machine_task_indianic.ui.base.BaseFragment
import com.example.machine_task_indianic.ui.viewModel.DatabaseViewModel
import com.example.machine_task_indianic.ui.viewModel.HomeViewModel
import com.example.machine_task_indianic.utils.Status
import com.example.machine_task_indianic.utils.hide
import com.example.machine_task_indianic.utils.show
import com.example.machine_task_indianic.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class CurrentWeatherFragment : BaseFragment<FragmentCurrentWeatherBinding>() {
    private val databaseViewModel: DatabaseViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        callWeather()
    }

    private fun callWeather() {
        val parameter = Parameter()
        parameter.lat = homeViewModel.latitude.toString()
        parameter.lon = homeViewModel.longitude.toString()
        homeViewModel.getWeather(parameter)
    }

    private fun initObserver() {
        homeViewModel.weatherLivaData.observe(requireActivity(), {
            when (it.status) {
                Status.SUCCESS -> {
                    mViewBinding.progress.hide()
                    it.data.let { res ->
                        res?.let {
                            setData(it)
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

    private fun setData(weatherData: WeatherData) {
        val latitude =  weatherData.coord.lat.toString()
        val longitude =  weatherData.coord.lon.toString()
        val address =  getAddress(weatherData.coord.lat,weatherData.coord.lon)
        mViewBinding.let {
            it.textViewLatitude.text = resources.getString(R.string.latitude, latitude)
            it.textViewLongitude.text = resources.getString(R.string.longitude,longitude)
            it.textViewAddress.text = resources.getString(R.string.address,address)
        }

        if(!homeViewModel.isDataAdded){
            GlobalScope.launch(Dispatchers.Main) { // Dispatchers.Main because only the Main thread can touch UI elements. Otherwise you may wish to use Dispatchers.IO instead!
                withContext(Dispatchers.Main) {
                    databaseViewModel.addWeatherData(LocationHistoryData(0,latitude,longitude,address))
                }
            }
        }

    }

    override fun getViewBinding(): FragmentCurrentWeatherBinding =
        FragmentCurrentWeatherBinding.inflate(layoutInflater)
}