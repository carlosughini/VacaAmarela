package com.vacaamarela.carlos.vacaamarela.model

import android.support.v7.app.AppCompatActivity
import java.io.Serializable

class Acougue(var nome: String, var endereco: String, var cidade: String, var latitude :  String, var longitude: String, var telefone: String) : Serializable {

    /** Distance of the butchery from the user */
    var mDistanceFromTheUser: String? = null

    /** Image resource ID for the butchery */
//    var mButcheryLogoResourceId: Int,


}




