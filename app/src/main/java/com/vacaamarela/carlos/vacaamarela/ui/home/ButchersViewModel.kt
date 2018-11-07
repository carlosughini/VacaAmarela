package com.vacaamarela.carlos.vacaamarela.ui.home

import android.arch.lifecycle.ViewModel
import com.vacaamarela.carlos.vacaamarela.Butcheryyy
import com.vacaamarela.carlos.vacaamarela.model.Butchery

class ButchersViewModel : ViewModel() {

    var userLatitude: Double? = null
    var userLongitude: Double? = null
    var butchery: Butchery? = null

    fun updateButchery(butchery: Butchery) {
        this.butchery = butchery
    }

}