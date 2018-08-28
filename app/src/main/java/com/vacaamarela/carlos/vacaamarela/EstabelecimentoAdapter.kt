package com.vacaamarela.carlos.vacaamarela

import android.content.Context
import android.widget.ArrayAdapter

class EstabelecimentoAdapter : ArrayAdapter<Estabelecimento> {

    constructor(context: Context, resource: Int, objects: MutableList<Estabelecimento>) : super(context, resource, objects) { }


}