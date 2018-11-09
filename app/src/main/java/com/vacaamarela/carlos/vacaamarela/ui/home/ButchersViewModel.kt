package com.vacaamarela.carlos.vacaamarela.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.ClipData
import com.vacaamarela.carlos.vacaamarela.Butcheryyy
import com.vacaamarela.carlos.vacaamarela.model.Butchery

class ButchersViewModel : ViewModel() {

    val userLatitude = MutableLiveData<Double>()
    val userLongitude = MutableLiveData<Double>()
    var butchery: Butchery? = null

    fun updateButchery(butchery: Butchery) {
        this.butchery = butchery
    }

    fun updateUserGeoLocation(latitude: Double, longitude: Double){
        userLatitude.value = latitude
        userLongitude.value = longitude
    }

}