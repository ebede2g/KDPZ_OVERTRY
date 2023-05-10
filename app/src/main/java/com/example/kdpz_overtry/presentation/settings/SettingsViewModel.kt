package com.example.kdpz_overtry.presentation.settings

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.kdpz_overtry.data.retrofit.WeatherClass
import com.example.kdpz_overtry.domain.WeatherUseCase
import com.example.newnews.data.factories.ApiRun
import kotlinx.coroutines.launch

class SettingsViewModel: ViewModel() {
    val weatherLiveData = MutableLiveData<WeatherClass?>()
    fun getWeather(cityName: String) {
        viewModelScope.launch {
            try {
                val weather = WeatherUseCase.getWeather(cityName)

                weather!!.cityName = cityName
                weatherLiveData.postValue(weather)

                Log.d("data", "data receive: success")
            }
            catch (e: Exception) {
                weatherLiveData.postValue(null)

                Log.d("data", "data receive: failure")
            }
        }
    }
}