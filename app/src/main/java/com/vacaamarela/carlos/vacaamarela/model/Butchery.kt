package com.vacaamarela.carlos.vacaamarela.model

import java.io.Serializable

class Butchery(var name: String?,
               var address: String?,
               var city: String?,
               var latitude :  Double?,
               var longitude: Double?,
               var elevation: Double?,
               var phoneNumber: String?,
               var distanceFromUser: String?) : Serializable {

    /** Distance of the butcheryyy from the user */
    var mDistanceFromTheUser: String? = null

    /** Image resource ID for the butcheryyy */
//    var mButcheryLogoResourceId: Int,





}




