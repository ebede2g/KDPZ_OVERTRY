package com.example.kdpz_overtry.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.view.View
import android.widget.Toast
import com.example.kdpz_overtry.R
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.kdpz_overtry.data.models.Weather
import com.example.kdpz_overtry.data.retrofit.WeatherClass
import com.example.kdpz_overtry.databinding.FragmentMainBinding
import com.example.kdpz_overtry.presentation.adapter.ListOfCities.listCity
import com.example.newnews.data.factories.ApiRun
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor


class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var binding: FragmentMainBinding

    private val args: MainFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        val cityName = args.cityNAME


        val api = ApiRun.productAPi //взяв тарілку

        lifecycleScope.launch {
            val weatherClass = api.getProductById(cityName)
            binding.currentTemp.text = weatherClass
                .main.temp.toString()

        }



        binding.cityName.text = "Ваше місто - $cityName"


        binding.setings.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSettingsFragment()
            findNavController().navigate(action)
        }


    }
}