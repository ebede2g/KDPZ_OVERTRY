package com.example.kdpz_overtry.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.kdpz_overtry.R
import com.bumptech.glide.Glide
import com.example.kdpz_overtry.data.retrofit.WeatherClass
import com.example.kdpz_overtry.databinding.FragmentMainBinding
import com.example.kdpz_overtry.presentation.settings.SettingsFragment


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    private var weather: WeatherClass? = null

    lateinit var viewmodel:MainDataViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        viewmodel = ViewModelProvider(this).get(MainDataViewModel::class.java)

        viewmodel.weatherLiveData.observe(viewLifecycleOwner) {
            binding.cityName.text = weather!!.cityName
            binding.currentTemp.text = "${weather!!.main.temp}°C"
            binding.weatherDescr.text = weather!!.weather[0].description
            Glide.with(binding.weatherImage.context)
                .load("https://openweathermap.org/img/wn/${weather!!.weather[0].icon}@2x.png") // в UseCase
                .into(binding.weatherImage)
        }





        setFragmentResultListener(SettingsFragment.REQUEST_KEY) { key, bundle ->
            val weather = bundle.getParcelable<WeatherClass?>("weather")
            if(weather!=null){
                viewmodel.saveData(weather)
            }
            Log.d("data", "weather received")

            this.weather = weather
            //mainDataViewModel.weather = weather

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