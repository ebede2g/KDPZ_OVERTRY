package com.example.kdpz_overtry.presentation.fragments.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kdpz_overtry.R
import com.example.kdpz_overtry.databinding.FragmentMainBinding
import com.example.kdpz_overtry.presentation.fragments.setting.SettingsFragment

class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var binding: FragmentMainBinding
    private var cityName = "ternopil"

    private var kostylState = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        val weatherViewModel = ViewModelProvider(this).get(MainViewMolel::class.java)
        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner) { weather ->
            if(weather != null) {
                binding.cityName.text = cityName

                binding.currentTemp.text = "${weather.main.temp}°C"
                binding.weatherDescr.text = weather.weather[0].description
                Glide.with(binding.weatherImage.context)
                        .load("https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png")
                        .into(binding.weatherImage)
            }
            else{
                binding.cityName.text = "Місто не знайдено"
                binding.currentTemp.text = "~~ C°"
                binding.weatherDescr.text = "Дані відсутні"
                Glide.with(binding.weatherImage.context)
                        .load("https://media.discordapp.net/attachments/906674727492415498/1098522717097054239/ya_na_mori25.gif")
                        .into(binding.weatherImage)
            }
        }
        if (kostylState) {
            weatherViewModel.getWeather(cityName)
            kostylState = false
        }

        setFragmentResultListener(SettingsFragment.REQUEST_KEY) { key, bundle ->
            val name = bundle.getString("data")

            if (name != null) {
                cityName = name
                weatherViewModel.getWeather(cityName)
            }
        }

        binding.setings.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSettingsFragment()
            findNavController().navigate(action)
        }

    }
}