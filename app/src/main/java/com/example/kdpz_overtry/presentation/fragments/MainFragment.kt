package com.example.kdpz_overtry.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.view.View
import com.example.kdpz_overtry.R
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kdpz_overtry.databinding.FragmentMainBinding
import com.example.newnews.data.factories.ApiRun
import kotlinx.coroutines.launch


class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var binding: FragmentMainBinding

    private val args: MainFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        val cityName = args.cityNAME


        val api = ApiRun.setApi //взяв тарілку

        lifecycleScope.launch {
            val weatherClass = api.getProductById(cityName)
            binding.cityName.text = cityName
            binding.currentTemp.text = "${weatherClass.main.temp.toString()}°C"
            binding.weatherDescr.text=weatherClass.weather[0].description
            Glide.with(binding.weatherImage.context).load("https://openweathermap.org/img/wn/${weatherClass.weather[0].icon}@2x.png").into(binding.weatherImage)
        }


        binding.setings.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSettingsFragment()
            findNavController().navigate(action)
        }


    }
}