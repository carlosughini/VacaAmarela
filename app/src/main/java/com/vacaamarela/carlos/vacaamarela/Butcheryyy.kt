package com.vacaamarela.carlos.vacaamarela

import android.support.v7.app.AppCompatActivity
import java.io.Serializable

class Butcheryyy : Serializable {

    /** Butcheryyy name */
    var mButcheryName: String? = null

    /** Address of the butcheryyy */
    var mButcheryAddress: String? = null

    /** Butcheryyy city */
    var mButcheryCity: String? = null

    /** Distance of the butcheryyy from the user */
    var mDistanceFromTheUser: String? = null

    /** Butcheryyy latitude */
    var mLatitude: String? = null

    /** Butcheryyy longitude */
    var mLongitude: String? = null

    /** Butcheryyy longitude */
    var mPhoneNumber: String? = null

    /** Image resource ID for the butcheryyy */
//    var mButcheryLogoResourceId: Int,

    constructor(butcheryName: String, butcheryAddress: String, butcheryCity: String, latitude: String, longitude: String, phoneNumber: String) {
        //mDistanceFromTheUser = distanceFromTheUser
        mButcheryName = butcheryName
        mButcheryAddress = butcheryAddress
        mButcheryCity = butcheryCity
        mLatitude = latitude
        mLongitude = longitude
        mPhoneNumber = phoneNumber
    }
}




