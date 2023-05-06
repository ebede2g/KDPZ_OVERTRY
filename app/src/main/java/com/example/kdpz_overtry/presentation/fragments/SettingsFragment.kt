package com.example.kdpz_overtry.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kdpz_overtry.R
import com.example.kdpz_overtry.databinding.FragmentSettingsBinding
import com.example.kdpz_overtry.presentation.adapter.CityAdapter
import com.example.kdpz_overtry.presentation.adapter.ListOfCities.listCity
import com.example.newnews.data.factories.ApiRun
import kotlinx.coroutines.*


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
            Log.d("beb", "start")
            val cityName = binding.cytyName.text.toString()
            var ie = false
            CoroutineScope(Dispatchers.Main).launch {
                Log.d("beb", "2")
                try {
                    val temp = ApiRun.setApi.getProductById(cityName)
                    if (temp.cod == 200) {
                        Log.d("beb", "2.1 compared")
                        ie = true
                        Log.d("beb", "2.2 ie = $ie (APPROVE)")
                    }

                } catch (e: Exception) {
                    Log.e("beb", "API call failed", e)
                }
                Log.d("beb", "2.3 ie = $ie (APPROVE)")
                Log.d("beb", "3 kod - $ie - not approve")
                if (ie) {
                    Log.d("beb", "kod - 200")
                    findNavController().navigate(
                        SettingsFragmentDirections.actionSettingsFragmentToMainFragment(
                            cityName
                        )
                    )
                } else {
                    Log.d("beb", "kod != 200")
                    Toast.makeText(requireContext(), "Місто не знайдене", Toast.LENGTH_SHORT).show()
                }
                Log.d("beb", "end")
            }
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




