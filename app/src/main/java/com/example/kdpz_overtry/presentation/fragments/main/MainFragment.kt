package com.example.kdpz_overtry.presentation.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.kdpz_overtry.R
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kdpz_overtry.data.retrofit.WeatherClass
import com.example.kdpz_overtry.databinding.FragmentMainBinding
import com.example.kdpz_overtry.presentation.fragments.setting.SettingsFragment
import com.example.newnews.data.factories.ApiRun
import kotlinx.coroutines.launch


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    private var weather: WeatherClass? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        val mainDataViewModel = ViewModelProvider(this).get(MainDataViewModel::class.java)
        weather = mainDataViewModel.weather

        if (weather != null) {
            binding.cityName.text = weather!!.cityName
            binding.currentTemp.text = "${weather!!.main.temp}°C"
            binding.weatherDescr.text = weather!!.weather[0].description
            Glide.with(binding.weatherImage.context)
                .load("https://openweathermap.org/img/wn/${weather!!.weather[0].icon}@2x.png")
                .into(binding.weatherImage)
        } else {
            binding.cityName.text = "Виберіть місто"
            binding.currentTemp.text = "~~ °C"
            binding.weatherDescr.text = "Дані відсутні"
            Glide.with(binding.weatherImage.context)
                .load(R.drawable.ya_na_mori25)
                .into(binding.weatherImage)
        }

        setFragmentResultListener(SettingsFragment.REQUEST_KEY) { key, bundle ->
            val weather = bundle.getParcelable<WeatherClass?>("weather")

            Log.d("data", "weather received")

            this.weather = weather
            mainDataViewModel.weather = weather

            binding.cityName.text = weather!!.cityName
            binding.currentTemp.text = "${weather!!.main.temp}°C"
            binding.weatherDescr.text = weather!!.weather[0].description
            Glide.with(binding.weatherImage.context)
                .load("https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png")
                .into(binding.weatherImage)

        }

        binding.setings.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSettingsFragment()
            findNavController().navigate(action)
        }

    }
}