package com.example.machine_task_indianic.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.machine_task_indianic.R
import com.example.machine_task_indianic.databinding.FragmentWeatherInfoBinding
import com.example.machine_task_indianic.ui.base.BaseFragment
import com.example.machine_task_indianic.utils.ViewPagerAdapter

class WeatherInfoFragment : BaseFragment<FragmentWeatherInfoBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(CurrentWeatherFragment(), resources.getString(R.string.current_weather))
        adapter.addFragment(ForecastFragment(), resources.getString(R.string.days_forecast))
        mViewBinding.viewPagerWeather.adapter = adapter
        mViewBinding.tabLayoutWeather.setupWithViewPager( mViewBinding.viewPagerWeather)

    }

    override fun getViewBinding(): FragmentWeatherInfoBinding =
        FragmentWeatherInfoBinding.inflate(layoutInflater)
}