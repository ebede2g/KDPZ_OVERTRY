package com.example.kdpz_overtry.presentation.fragments.setting

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kdpz_overtry.R
import com.example.kdpz_overtry.databinding.FragmentSettingsBinding
import com.example.kdpz_overtry.presentation.cityList.CityAdapter
import com.example.kdpz_overtry.data.local.ListOfCities

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private lateinit var binding: FragmentSettingsBinding
    private val adapter = CityAdapter() {
        setFragmentResult(REQUEST_KEY, bundleOf("data" to it))
        findNavController().navigateUp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        binding.apply {
            rcView.layoutManager = LinearLayoutManager(context)
            rcView.adapter = adapter
        }
        adapter.addCities(ListOfCities.listCity)

        binding.apply.setOnClickListener{
            val chosenCityName = binding.cytyName.text.toString()

            setFragmentResult(REQUEST_KEY, bundleOf("data" to chosenCityName))
            findNavController().navigateUp()
        }

    }

    companion object {
        const val REQUEST_KEY = "request key"
    }
}