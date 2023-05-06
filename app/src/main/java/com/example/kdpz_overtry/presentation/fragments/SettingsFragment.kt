package com.example.kdpz_overtry.presentation.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kdpz_overtry.R
import com.example.kdpz_overtry.data.retrofit.WeatherClass
import com.example.kdpz_overtry.databinding.FragmentSettingsBinding
import com.example.kdpz_overtry.presentation.adapter.CityAdapter
import com.example.kdpz_overtry.presentation.adapter.ListOfCities.listCity
import com.example.newnews.data.factories.ApiRun
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait


class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private lateinit var binding: FragmentSettingsBinding
    val adapter = CityAdapter() {
        val direction = SettingsFragmentDirections.actionSettingsFragmentToMainFragment(it)
        findNavController().navigate(direction)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)








        binding.apply.setOnClickListener {
            Log.d("beb","start")
            val cityName = binding.cytyName.text.toString()
            var ie = false
            GlobalScope.launch {
                Log.d("beb","2")
                val temp = ApiRun.productAPi.getProductById(cityName)
                Log.d("beb","2.0 start ifelse")
                if(temp.cod == 200) {
                    Log.d("beb","2.1 compared")
                   ie = true
                    Log.d("beb","2.2 ie = $ie (APROVE)")
                }
                Log.d("beb","2.3 ie = $ie (APROVE)")
            }
            Thread.sleep(1000)
            Log.d("beb","3 kod - $ie - not aprove")
            if(ie) {
                Log.d("beb","kod - 200")
                findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToMainFragment(cityName))
            }
            else {
                Log.d("beb","kod != 200")
                Toast.makeText(requireContext(), "City not found",Toast.LENGTH_SHORT).show()
                findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToMainFragment("тернопіль"))
            }
            Log.d("beb","end")
        }




        val setMyCityButton = activity?.findViewById<Button>(R.id.exit)
        setMyCityButton?.setOnClickListener {
            val cityName = view.findViewById<TextView>(R.id.cityName)
            val action =
                SettingsFragmentDirections.actionSettingsFragmentToMainFragment(cityName.text.toString())
            findNavController().navigate(action)
        }


        binding.apply {
            rcView.layoutManager = LinearLayoutManager(context)
            rcView.adapter = adapter
        }
        adapter.addCities(listCity)
    }


}




