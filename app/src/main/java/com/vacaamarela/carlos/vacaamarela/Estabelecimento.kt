package com.vacaamarela.carlos.vacaamarela

import android.support.v7.app.AppCompatActivity

class Estabelecimento {
    /** Distance of the butchery from the user */
    var mDistanceFromTheUser: String? = null

    /** Image resource ID for the butchery */
//    var mButcheryLogoResourceId: Int,

    /** Butchery name */
    var mButcheryName: String? = null

    constructor(distanceFromTheUser: String, butcheryName: String) {
        mDistanceFromTheUser = distanceFromTheUser
        mButcheryName = butcheryName
    }
}




