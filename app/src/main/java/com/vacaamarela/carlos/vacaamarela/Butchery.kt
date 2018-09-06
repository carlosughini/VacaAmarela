package com.vacaamarela.carlos.vacaamarela

import android.support.v7.app.AppCompatActivity
import java.io.Serializable

class Butchery : Serializable {

    /** Butchery name */
    var mButcheryName: String? = null

    /** Address of the butchery */
    var mButcheryAddress: String? = null

    /** Butchery city */
    var mButcheryCity: String? = null

    /** Distance of the butchery from the user */
    var mDistanceFromTheUser: String? = null

    /** Image resource ID for the butchery */
//    var mButcheryLogoResourceId: Int,

    constructor(butcheryName: String, butcheryAddress: String, butcheryCity: String) {
        //mDistanceFromTheUser = distanceFromTheUser
        mButcheryName = butcheryName
        mButcheryAddress = butcheryAddress
        mButcheryCity = butcheryCity
    }
}




