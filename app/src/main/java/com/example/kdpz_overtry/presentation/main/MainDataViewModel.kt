package com.examplefun.kdpz_overtry.presentation.main
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainDataViewModel : ViewModel() {
    //для збереження даних при повороті екрану
    val weatherLiveData = MutableLiveData<MainWeatherClass>()

    fun saveData(weather: MainWeatherClass) {
        weatherLiveData.postValue(weather)
    }

}