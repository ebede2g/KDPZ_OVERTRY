package com.examplefun saveData(weather: WeatherClass){
        weatherLiveData.postValue(weather)
    }.kdpz_overtry.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kdpz_overtry.data.retrofit.WeatherClass

class MainDataViewModel:ViewModel() {
    var weather: WeatherClass? = null   //для збереження даних при повороті екрану
    val weatherLiveData = MutableLiveData<WeatherClass>()

    fun saveData(weather: WeatherClass){
        weatherLiveData.postValue(weather)
    }

}